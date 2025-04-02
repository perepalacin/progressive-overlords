package progressive_overlords.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.services.ExercisesService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoutinesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ExercisesService exercisesService;

    public boolean findIfExists (int routineId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return false;
        }
        String sql = """
            SELECT
                wt.id
            FROM workouts wt
            WHERE wt.user_id = ? AND wt.id = ? AND wt.template_id IS NULL AND wt.is_template = true
        """;

        List<Integer> routines = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return (rs.getInt("id"));
        }, userId, routineId);

        return !routines.isEmpty();

    }

    public WorkoutDao getById(int routineId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String sql = """
            SELECT
                wt.id,
                wt.name,
                wt.description,
                COALESCE(json_agg(
                    json_build_object(
                        'exerciseId', wte.exercise_id,
                        'exerciseNum', wte.exercise_num,
                        'setNum', wte.set_num,
                        'weight', wte.weight,
                        'reps', wte.reps,
                        'warmup', wte.is_warmup
                    )
                ) FILTER (WHERE wte.exercise_id IS NOT NULL), '[]') AS exercises
            FROM workouts wt
            LEFT JOIN workout_exercises wte ON wt.id = wte.workout_id
            WHERE wt.user_id = ? AND wt.id = ? AND wt.template_id IS NULL AND wt.is_template = true
            GROUP BY wt.id
        """;

        List<WorkoutDao> templateList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String setsJson = rs.getString("exercises");

            List<SetDao> setList = null;
            try {
                setList = new ObjectMapper().readValue(
                        setsJson,
                        new com.fasterxml.jackson.core.type.TypeReference<>() {}
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            WorkoutDao routine = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();

            routine.setExercises(exercisesService.generateExerciseListFromSets(setList));

            return routine;
        }, userId, routineId);

        return templateList.isEmpty() ? null : templateList.get(0);
    }

    public WorkoutDao saveRoutine (WorkoutDao routine) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, is_template, user_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, routine.getName());
            ps.setString(2, routine.getDescription() != null ? routine.getDescription() : "");
            ps.setBoolean(3, true);
            ps.setObject(4, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated routine ID");
        }

        int routineId = ((Number) keys.get("id")).intValue();

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<SetDao> sets = routine.getFlatSetsList();
        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
                (ps, set) -> {
                    ps.setInt(1, routineId);
                    ps.setInt(2, set.getExerciseId());
                    ps.setInt(3, set.getSetNum());
                    ps.setFloat(4, set.getWeight());
                    ps.setFloat(5, set.getReps());
                    ps.setObject(6, userId);
                    ps.setInt(7, set.getExerciseNum());
                    ps.setBoolean(8, set.isWarmup());
                });

        routine.setId(routineId);
        return routine;
    }

    public WorkoutDao updateRoutine (WorkoutDao routine) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String updateRoutineSQL = """
            UPDATE workouts
            SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ? AND user_id = ? AND is_template = true AND template_id IS NULL
        """;

        jdbcTemplate.update(updateRoutineSQL,
                routine.getName(),
                routine.getDescription() != null ? routine.getDescription() : "",
                routine.getId(),
                userId
        );

        String deleteExercisesSQL = """
            DELETE FROM workout_exercises
            WHERE workout_id = ? AND user_id = ?
        """;
        jdbcTemplate.update(deleteExercisesSQL, routine.getId(), userId);

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<SetDao> sets = routine.getFlatSetsList();
        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
                (ps, set) -> {
                    ps.setInt(1, routine.getId());
                    ps.setInt(2, set.getExerciseId());
                    ps.setInt(3, set.getSetNum());
                    ps.setFloat(4, set.getWeight());
                    ps.setFloat(5, set.getReps());
                    ps.setObject(6, userId);
                    ps.setInt(7, set.getExerciseNum());
                    ps.setBoolean(8, set.isWarmup());
                });

        return routine;
    }

    public boolean delete (int routineId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return false;
        }

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM workouts WHERE id = ? AND user_id = ?");
                ps.setInt(1, routineId);
                ps.setObject(2, userId);
                return ps;
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
