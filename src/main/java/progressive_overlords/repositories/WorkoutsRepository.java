package progressive_overlords.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dao.WorkoutDao;

@Repository
@RequiredArgsConstructor
public class WorkoutsRepository {

    private final JdbcTemplate jdbcTemplate;

    public int createAndStartWorkout(TemplateDao template, UUID userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts (name, description, color, body_part, tags, user_id, workout_template_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, template != null ? template.getName() : "");
            ps.setString(2, template != null && template.getDescription() != null ? template.getDescription() : "");
            ps.setString(3, template != null && template.getColor() != null ? template.getColor() : "#CD4945");
            ps.setString(4, template != null ? template.getBodyPart() : null);
            ps.setString(5, template != null && template.getTags() != null ? template.getUnparsedTags() : "");
            ps.setObject(6, userId);
            ps.setObject(7, template != null ? template.getId() : null);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout template ID");
        }

        return ((Number) keys.get("id")).intValue();
    }

    public void endWorkout(int workoutId, UUID userId) {
        String updateTemplateSQL = """
            UPDATE workout_templates
            SET end_at = CURRENT_TIMESTAMP
            WHERE id = ? AND user_id = ?
        """;
        jdbcTemplate.update(updateTemplateSQL, workoutId, userId);
    }

    public WorkoutDao getById(int workoutId, UUID userId) {
        String sql = """
            SELECT
                w.id,
                w.name,
                w.description,
                w.color,
                w.body_part,
                w.tags,
                w.created_at,
                w.started_at,
                w.ended_at,
                w.updated_at,
                w.workout_template_id,
                w.user_id,
                COALESCE(json_agg(
                    json_build_object(
                        'exerciseId', we.exercise_id,
                        'setNum', we.set_num,
                        'weight', we.weight,
                        'reps', we.reps,
                        'annotation', we.annotation
                    )
                ) FILTER (WHERE we.id IS NOT NULL), '[]') AS exercises
            FROM workouts w
            LEFT JOIN workout_exercises we ON w.id = we.workout_id
            WHERE w.id = ? AND w.user_id = ?
            GROUP BY w.id
        """;

        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, new Object[]{workoutId, userId},
                (rs, rowNum) -> this.parseWorkoutResultSet(rs, rowNum)
        );

        return workoutList.get(0);
    }

    public WorkoutDao getLastByTemplateId(int templateId, UUID userId) {
        String sql = """
            SELECT
                w.id,
                w.name,
                w.description,
                w.color,
                w.body_part,
                w.tags,
                w.created_at,
                w.started_at,
                w.ended_at,
                w.updated_at,
                w.workout_template_id,
                w.user_id,
                COALESCE(json_agg(
                    json_build_object(
                        'exerciseId', we.exercise_id,
                        'setNum', we.set_num,
                        'weight', we.weight,
                        'reps', we.reps,
                        'annotation', we.annotation
                    )
                ) FILTER (WHERE we.id IS NOT NULL), '[]') AS exercises
            FROM workouts w
            LEFT JOIN workout_exercises we ON w.id = we.workout_id
            WHERE w.workout_template_id = ? AND w.user_id = ?
            GROUP BY w.id
            ORDER BY ended_at DESC
            LIMIT 1
        """;

        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, new Object[]{templateId, userId},
                (rs, rowNum) -> this.parseWorkoutResultSet(rs, rowNum)
        );
        if (workoutList.isEmpty()) {
            return null;
        }
        return workoutList.get(0);
    }

    private WorkoutDao parseWorkoutResultSet (ResultSet rs, int rowNum) throws SQLException {

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

            return new WorkoutDao(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("color"),
                    rs.getString("body_part"),
                    rs.getString("tags"),
                    rs.getString("started_at"),
                    rs.getString("ended_at") != null ? rs.getString("ended_at") : null,
                    exercisesId,
                    sets,
                    reps
            );
        }

}
