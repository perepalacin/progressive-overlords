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
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.ExercisesService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ExercisesService exercisesService;

    public WorkoutDao getById (int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String sql = """
            SELECT
                wt.id,
                wt.name,
                wt.description,
                wt.is_template,
                wt.template_id,
                wt.started_at,
                wt.ended_at,
                wt.user_id,
                COALESCE(json_agg(
                    json_build_object(
                        'id', wte.id,
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
            WHERE wt.id = ? AND wt.is_template = false
            GROUP BY wt.id
        """;

        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String setsJson = rs.getString("exercises");

            List<SetDao> setList;
            try {
                setList = new ObjectMapper().readValue(
                        setsJson,
                        new com.fasterxml.jackson.core.type.TypeReference<>() {}
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            WorkoutDao workout = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .templateId(rs.getInt("template_id"))
                    .isTemplate(rs.getBoolean("is_template"))
                    .startDate(rs.getString("started_at"))
                    .endDate(rs.getString("ended_at"))
                    .userId((UUID) rs.getObject("user_id"))
                    .build();

            if (setList != null && !setList.isEmpty()) {
                workout.setExercises(exercisesService.generateExerciseListFromSets(setList, workoutId));
            }

            return workout;
        }, workoutId);

        return workoutList.isEmpty() ? null : workoutList.get(0);
    }

    public boolean findIfExists (int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sql = """
            SELECT
                wt.id
            FROM workouts wt
            WHERE wt.user_id = ? AND wt.id = ? AND wt.is_template = false
        """;

        List<Integer> routines = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return (rs.getInt("id"));
        }, userId, workoutId);

        return !routines.isEmpty();

    }

    public int startWorkout (WorkoutDto workoutDto) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (workoutDto.getTemplateId() != null) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, workoutDto.getName());
                ps.setString(2, workoutDto.getDescription());
                ps.setBoolean(3, false);
                ps.setObject(4, workoutDto.getTemplateId());
                ps.setObject(5, userId);
                return ps;
            }, keyHolder);
        } else {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO workouts (name, description, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, workoutDto.getName());
                ps.setString(2, workoutDto.getDescription());
                ps.setBoolean(3, false);
                ps.setObject(4, null);
                ps.setObject(5, userId);
                return ps;
            }, keyHolder);
        }

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout Id");
        }

        return ((Number) keys.get("id")).intValue();
    }

    public void updateWorkoutEndDate(int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String sqlStatement = """
                UPDATE workouts SET
                ended_at = CURRENT_TIMESTAMP,
                updated_at = CURRENT_TIMESTAMP
                WHERE id = ? AND user_id = ?
                """;
        jdbcTemplate.update(sqlStatement, workoutId, userId);
    }

    public boolean delete (int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM workouts WHERE id = ? AND user_id = ?");
                ps.setInt(1, workoutId);
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
