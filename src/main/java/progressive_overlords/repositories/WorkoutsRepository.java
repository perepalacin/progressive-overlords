package progressive_overlords.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkoutsRepository {

    private final JdbcTemplate jdbcTemplate;

    //TODO: Same method but get the least with templateid = x
    public WorkoutDao getWorkoutById(int workoutId, UUID userId) {
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

        List<WorkoutDao> workoutList = jdbcTemplate.query(sql, new Object[]{workoutId, userId}, (rs, rowNum) -> {

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
                    rs.getTimestamp("started_at").toLocalDateTime(),
                    rs.getTimestamp("ended_at") != null ? rs.getTimestamp("ended_at").toLocalDateTime() : null,
                    exercisesId,
                    sets,
                    reps
            );
        });

        return workoutList.isEmpty() ? null : workoutList.get(0);
    }

}
