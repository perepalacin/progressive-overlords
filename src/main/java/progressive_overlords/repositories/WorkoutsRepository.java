package progressive_overlords.repositories;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;

@Repository
@RequiredArgsConstructor
public class WorkoutsRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<WorkoutDao> getAllTemplatesFromUser () {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }
        String sqlStatement = "SELECT id, name, description, color, tags FROM workouts WHERE template_id IS NULL AND is_template = true AND user_id = ?";
        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            WorkoutDao workout = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .color(rs.getString("color"))
                    .unparsedTags(rs.getString("tags"))
                    .build();
            workout.parseTags(workout.getUnparsedTags());
            return workout;
        }, userId);
    }

    public WorkoutDao getTemplateById(int templateId) {

        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String sql = """
            SELECT
                wt.id,
                wt.name,
                wt.description,
                wt.color,
                wt.tags,
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

            WorkoutDao template = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .color(rs.getString("color"))
                    .unparsedTags(rs.getString("tags"))
            .build();

            template.setExercisesDaoFromSetsDao(setList);
            template.parseTags(template.getUnparsedTags());

            return template;
        }, userId, templateId);

        return templateList.isEmpty() ? null : templateList.get(0);
    }

    public int startWorkout (WorkoutDto template) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return 0;
            // TODO: Throw an exception to unauthorized request maybe?
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, color, tags, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, template.getName());
            ps.setString(2, template.getDescription());
            ps.setString(3, template.getColor());
            ps.setString(4, template.getUnparsedTags());
            ps.setBoolean(5, false);
            ps.setObject(6, template.getTemplateId());
            ps.setObject(7, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout Id");
        }

        return ((Number) keys.get("id")).intValue();
    }

    public void finishWorkout (int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return;
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String updateWorkoutSQL = """
            UPDATE workouts
            SET updated_at = CURRENT_TIMESTAMP, ended_at = CURRENT_TIMESTAMP
            WHERE id = ? AND user_id = ?
        """;

        jdbcTemplate.update(updateWorkoutSQL,
                workoutId,
                userId
        );
    }

    public WorkoutDao getWorkoutById(int workoutId) {

        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String sql = """
            SELECT
                w.id,
                w.name,
                w.description,
                w.color,
                w.tags,
                w.template_id,
                w.started_at,
                w.ended_at,
                COALESCE(json_agg(
                    json_build_object(
                        'exerciseId', we.exercise_id,
                        'setNum', we.set_num,
                        'weight', we.weight,
                        'reps', we.reps,
                        'exerciseNum', we.exercise_num,
                        'warmup', we.is_warmup
                    )
                ) FILTER (WHERE we.exercise_id IS NOT NULL), '[]') AS exercises
            FROM workouts w
            LEFT JOIN workout_exercises we ON w.id = we.workout_id
            WHERE w.user_id = ? AND w.id = ?
            GROUP BY w.id
        """;

        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String exercisesJson = rs.getString("exercises");

            List<SetDao> setList = null;
            try {
                setList = new ObjectMapper().readValue(
                        exercisesJson,
                        new com.fasterxml.jackson.core.type.TypeReference<>() {}
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            Integer templateId = rs.getObject("template_id") != null ? rs.getInt("template_id") : null;
            WorkoutDao workout = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .color(rs.getString("color"))
                    .unparsedTags(rs.getString("tags"))
                    .startDate(rs.getString("started_at"))
                    .endDate(rs.getString("ended_at"))
                    .templateId(templateId)
                    .build();

            workout.setExercisesDaoFromSetsDao(setList);
            workout.parseTags(workout.getUnparsedTags());

            return workout;
        }, userId, workoutId);

        return workoutList.isEmpty() ? null : workoutList.get(0);
    }

    public WorkoutDao saveTemplate (WorkoutDao template) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, color, tags, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, template.getName());
            ps.setString(2, template.getDescription() != null ? template.getDescription() : "");
            ps.setString(3, template.getColor() != null ? template.getColor() : "#CD4945");
            ps.setString(4, template.getUnparsedTags() != null ? template.getUnparsedTags() : "");
            ps.setBoolean(5, true);
            ps.setObject(6, null);
            ps.setObject(7, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout template ID");
        }

        int workoutTemplateId = ((Number) keys.get("id")).intValue(); // Correct casting

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<SetDao> sets = template.getFlatSetsList();
        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
                (ps, set) -> {
                    ps.setInt(1, workoutTemplateId);
                    ps.setInt(2, set.getExerciseId());
                    ps.setInt(3, set.getSetNum());
                    ps.setFloat(4, set.getWeight());
                    ps.setFloat(5, set.getReps());
                    ps.setObject(6, userId);
                    ps.setInt(7, set.getExerciseNum());
                    ps.setBoolean(8, set.isWarmup());
                });

        template.setId(workoutTemplateId);
        return template;

    }

    public WorkoutDao editTemplate(WorkoutDao template) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String updateTemplateSQL = """
            UPDATE workouts
            SET name = ?, description = ?, color = ?, tags = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ? AND user_id = ? AND is_template = true AND template_id IS NULL
        """;

        jdbcTemplate.update(updateTemplateSQL,
                template.getName(),
                template.getDescription() != null ? template.getDescription() : "",
                template.getColor() != null ? template.getColor() : "#CD4945",
                template.getTags() != null ? template.getUnparsedTags() : "",
                template.getId(),
                userId
        );

        String deleteExercisesSQL = """
            DELETE FROM workout_exercises
            WHERE workout_id = ? AND user_id = ?
        """;
        jdbcTemplate.update(deleteExercisesSQL, template.getId(), userId);

        List<Integer> updatedExerciseIds = template.getFlatSetsList().stream()
                .map(SetDao::getExerciseId)
                .toList();

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(insertExercisesSQL, template.getFlatSetsList(), template.getFlatSetsList().size(),
                (ps, set) -> {
                    ps.setInt(1, template.getId());
                    ps.setInt(2, set.getExerciseId());
                    ps.setInt(3, set.getSetNum());
                    ps.setFloat(4, set.getWeight());
                    ps.setFloat(5, set.getReps());
                    ps.setObject(6, userId);
                    ps.setInt(7, set.getSetNum());
                    ps.setBoolean(8, set.isWarmup());
                });

        template.setId(template.getId());
        return template;
    }

    public boolean deleteWorkout(int workoutId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return false;
        }
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
