package progressive_overlords.repositories;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
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

//    public List<WorkoutDao> getByTemplateIdAndUserId(Integer templateId) {
//        Object userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userId == null) {
//            return null;
//        }
//        String sql = "SELECT id, name, description, color, body_part, tags FROM workouts WHERE user_id = ?";
//        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
//            WorkoutDao workout = WorkoutDao.builder().id(rs.getInt("id")).name(rs.getString("name")).description(rs.getString("description")).color(rs.getString("color")).bodyPart(rs.getString("body_part")).unparsedTags(rs.getString("tags")).build();
//            workout.parseTags(workout.getUnparsedTags());
//            return workout;
//        });
//    }

    public List<WorkoutDao> getAllTemplatesFromUser () {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }
        String sqlStatement = "SELECT id, name, description, color, body_part, tags FROM workouts WHERE template_id IS NULL AND is_template = true AND user_id = ?";
        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            WorkoutDao workout = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .color(rs.getString("color"))
                    .bodyPart(rs.getString("body_part"))
                    .unparsedTags(rs.getString("tags"))
                    .build();
            workout.parseTags(workout.getUnparsedTags());
            return workout;
        }, userId);
    }

    public WorkoutDao getByTemplateId(int templateId) {

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
                wt.body_part,
                wt.tags,
                COALESCE(json_agg(
                    json_build_object(
                        'exerciseId', wte.exercise_id,
                        'setNum', wte.set_num,
                        'weight', wte.weight,
                        'reps', wte.reps
                    )
                ) FILTER (WHERE wte.exercise_id IS NOT NULL), '[]') AS exercises
            FROM workouts wt
            LEFT JOIN workout_exercises wte ON wt.id = wte.workout_id
            WHERE wt.user_id = ? AND wt.id = ? AND wt.template_id IS NULL AND wt.is_template = true
            GROUP BY wt.id
        """;

        List<WorkoutDao> templateList = jdbcTemplate.query(sql, (rs, rowNum) -> {
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

            List<Integer> exercisesId = setList.stream().map(SetDao::getExerciseId).toList();
            List<Integer> sets = setList.stream().map(SetDao::getSetNum).toList();
            List<Integer> reps = setList.stream().map(SetDao::getReps).toList();

            WorkoutDao template = WorkoutDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .color(rs.getString("color"))
                    .bodyPart(rs.getString("body_part"))
                    .unparsedTags(rs.getString("tags"))
            .build();

            template.parseSetsList(exercisesId, sets, reps);
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
                    "INSERT INTO workouts (name, description, color, body_part, tags, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, template.getName());
            ps.setString(2, template.getDescription());
            ps.setString(3, template.getColor());
            ps.setString(4, template.getBodyPart());
            ps.setString(5, template.getUnparsedTags());
            ps.setBoolean(6, false);
            ps.setObject(7, template.getTemplateId());
            ps.setObject(8, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout Id");
        }

        return ((Number) keys.get("id")).intValue();
    }

    public WorkoutDao saveTemplate (WorkoutDao template) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, color, body_part, tags, is_template, template_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, template.getName());
            ps.setString(2, template.getDescription() != null ? template.getDescription() : "");
            ps.setString(3, template.getColor() != null ? template.getColor() : "#CD4945");
            ps.setString(4, template.getBodyPart());
            ps.setString(5, template.getTags() != null ? template.getUnparsedTags() : "");
            ps.setBoolean(6, true);
            ps.setObject(7, null);
            ps.setObject(8, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout template ID");
        }

        int workoutTemplateId = ((Number) keys.get("id")).intValue(); // Correct casting

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps) VALUES (?, ?, ?, ?, ?)";

        List<SetDao> sets = template.getSets();
        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
                (ps, exercise) -> {
                    ps.setInt(1, workoutTemplateId);
                    ps.setInt(2, exercise.getExerciseId());
                    ps.setInt(3, exercise.getSetNum());
                    ps.setInt(4, exercise.getWeight());
                    ps.setInt(5, exercise.getReps());
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
            SET name = ?, description = ?, color = ?, body_part = ?, tags = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ? AND user_id = ? AND is_template = true AND template_id IS NULL
        """;

        jdbcTemplate.update(updateTemplateSQL,
                template.getName(),
                template.getDescription() != null ? template.getDescription() : "",
                template.getColor() != null ? template.getColor() : "#CD4945",
                template.getBodyPart(),
                template.getTags() != null ? template.getUnparsedTags() : "",
                template.getId(),
                userId
        );

        String deleteExercisesSQL = """
            DELETE FROM workout_exercises
            WHERE workout_id = ?
        """;
        jdbcTemplate.update(deleteExercisesSQL, template.getId());

        List<Integer> updatedExerciseIds = template.getSets().stream()
                .map(SetDao::getExerciseId)
                .toList();

        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(insertExercisesSQL, template.getSets(), template.getSets().size(),
                (ps, exercise) -> {
                    ps.setInt(1, template.getId());
                    ps.setInt(2, exercise.getExerciseId());
                    ps.setInt(3, exercise.getSetNum());
                    ps.setInt(4, exercise.getWeight());
                    ps.setInt(5, exercise.getReps());
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
            String preparedStatement = """
                DELETE FROM workout_templates
                WHERE id = ?
                AND user_id = ?
            """;

            jdbcTemplate.update(preparedStatement, workoutId, userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public List<WorkoutDao> getAllTemplatesByUserId(UUID userId) {
//        String sql = "SELECT id, name, description, color, body_part, tags FROM workouts WHERE user_id = ?";
//        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
//            WorkoutDao workout = WorkoutDao.builder().id(rs.getInt("id")).name(rs.getString("name")).description(rs.getString("description")).color(rs.getString("color")).bodyPart(rs.getString("body_part")).unparsedTags(rs.getString("tags")).build();
//            workout.parseTags(workout.getUnparsedTags());
//            return workout;
//        });
//    }
//
//    public WorkoutDao saveTemplate(WorkoutDao template, UUID userId) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "INSERT INTO workouts (name, description, color, body_part, tags, user_id, is_template, template_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, template.getName());
//            ps.setString(2, template.getDescription() != null ? template.getDescription() : "");
//            ps.setString(3, template.getColor() != null ? template.getColor() : "#CD4945");
//            ps.setString(4, template.getBodyPart());
//            ps.setString(5, template.getTags() != null ? template.getUnparsedTags() : "");
//            ps.setObject(6, userId);
//            ps.setBoolean(7, true);
//            ps.setObject(8, null);
//            return ps;
//        }, keyHolder);
//
//        Map<String, Object> keys = keyHolder.getKeys();
//        if (keys == null || !keys.containsKey("id")) {
//            throw new RuntimeException("Failed to retrieve generated workout template ID");
//        }
//
//        int workoutTemplateId = ((Number) keys.get("id")).intValue(); // Correct casting
//
//        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps) VALUES (?, ?, ?, ?, ?)";
//
//        List<SetDao> sets = template.getSets();
//        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
//                (ps, exercise) -> {
//                    ps.setInt(1, workoutTemplateId);
//                    ps.setInt(2, exercise.getExerciseId());
//                    ps.setInt(3, exercise.getSetNum());
//                    ps.setInt(4, exercise.getWeight());
//                    ps.setInt(5, exercise.getReps());
//                });
//
//        template.setId(workoutTemplateId);
//        template.parseTags(template.getUnparsedTags());
//        return template;
//    }
//
//    public int createAndStartWorkout(TemplateDao template, UUID userId) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "INSERT INTO workouts (name, description, color, body_part, tags, user_id, workout_template_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, template != null ? template.getName() : "");
//            ps.setString(2, template != null && template.getDescription() != null ? template.getDescription() : "");
//            ps.setString(3, template != null && template.getColor() != null ? template.getColor() : "#CD4945");
//            ps.setString(4, template != null ? template.getBodyPart() : null);
//            ps.setString(5, template != null && template.getTags() != null ? template.getUnparsedTags() : "");
//            ps.setObject(6, userId);
//            ps.setObject(7, template != null ? template.getId() : null);
//            return ps;
//        }, keyHolder);
//
//        Map<String, Object> keys = keyHolder.getKeys();
//        if (keys == null || !keys.containsKey("id")) {
//            throw new RuntimeException("Failed to retrieve generated workout template ID");
//        }
//
//        return ((Number) keys.get("id")).intValue();
//    }
//
//    public void endWorkout(int workoutId, UUID userId) {
//        String updateTemplateSQL = """
//            UPDATE workout_templates
//            SET end_at = CURRENT_TIMESTAMP
//            WHERE id = ? AND user_id = ?
//        """;
//        jdbcTemplate.update(updateTemplateSQL, workoutId, userId);
//    }
//
//    public WorkoutDao getById(int workoutId, UUID userId) {
//        String sql = """
//            SELECT
//                w.id,
//                w.name,
//                w.description,
//                w.color,
//                w.body_part,
//                w.tags,
//                w.created_at,
//                w.started_at,
//                w.ended_at,
//                w.updated_at,
//                w.workout_template_id,
//                w.user_id,
//                COALESCE(json_agg(
//                    json_build_object(
//                        'exerciseId', we.exercise_id,
//                        'setNum', we.set_num,
//                        'weight', we.weight,
//                        'reps', we.reps,
//                        'annotation', we.annotation
//                    )
//                ) FILTER (WHERE we.id IS NOT NULL), '[]') AS exercises
//            FROM workouts w
//            LEFT JOIN workout_exercises we ON w.id = we.workout_id
//            WHERE w.id = ? AND w.user_id = ?
//            GROUP BY w.id
//        """;
//
//        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, new Object[]{workoutId, userId},
//                (rs, rowNum) -> this.parseWorkoutResultSet(rs, rowNum)
//        );
//
//        return workoutList.get(0);
//    }
//
//    public WorkoutDao getLastByTemplateId(int templateId, UUID userId) {
//        String sql = """
//            SELECT
//                w.id,
//                w.name,
//                w.description,
//                w.color,
//                w.body_part,
//                w.tags,
//                w.created_at,
//                w.started_at,
//                w.ended_at,
//                w.updated_at,
//                w.workout_template_id,
//                w.user_id,
//                COALESCE(json_agg(
//                    json_build_object(
//                        'exerciseId', we.exercise_id,
//                        'setNum', we.set_num,
//                        'weight', we.weight,
//                        'reps', we.reps,
//                        'annotation', we.annotation
//                    )
//                ) FILTER (WHERE we.id IS NOT NULL), '[]') AS exercises
//            FROM workouts w
//            LEFT JOIN workout_exercises we ON w.id = we.workout_id
//            WHERE w.workout_template_id = ? AND w.user_id = ?
//            GROUP BY w.id
//            ORDER BY ended_at DESC
//            LIMIT 1
//        """;
//
//        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, new Object[]{templateId, userId},
//                (rs, rowNum) -> this.parseWorkoutResultSet(rs, rowNum)
//        );
//        if (workoutList.isEmpty()) {
//            return null;
//        }
//        return workoutList.get(0);
//    }
//
//    private WorkoutDao parseWorkoutResultSet (ResultSet rs, int rowNum) throws SQLException {
//
//            String exercisesJson = rs.getString("exercises");
//            List<SetDao> setList = null;
//            try {
//                setList = new ObjectMapper().readValue(
//                        exercisesJson,
//                        new com.fasterxml.jackson.core.type.TypeReference<>() {}
//                );
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//
//            List<Integer> exercisesId = setList.stream().map(SetDao::getExerciseId).toList();
//            List<Integer> sets = setList.stream().map(SetDao::getSetNum).toList();
//            List<Integer> reps = setList.stream().map(SetDao::getReps).toList();
//
//            return new WorkoutDao(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getString("description"),
//                    rs.getString("color"),
//                    rs.getString("body_part"),
//                    rs.getString("tags"),
//                    rs.getString("started_at"),
//                    rs.getString("ended_at") != null ? rs.getString("ended_at") : null,
//                    exercisesId,
//                    sets,
//                    reps
//            );
//        }
//
}
