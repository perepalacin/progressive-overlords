package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoutinesRepository {

    private final JdbcTemplate jdbcTemplate;

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

//        int workoutTemplateId = ((Number) keys.get("id")).intValue(); // Correct casting
//
//        String insertExercisesSQL = "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, weight, reps, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        List<SetDao> sets = template.getFlatSetsList();
//        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
//                (ps, set) -> {
//                    ps.setInt(1, workoutTemplateId);
//                    ps.setInt(2, set.getExerciseId());
//                    ps.setInt(3, set.getSetNum());
//                    ps.setFloat(4, set.getWeight());
//                    ps.setFloat(5, set.getReps());
//                    ps.setObject(6, userId);
//                    ps.setInt(7, set.getExerciseNum());
//                    ps.setBoolean(8, set.isWarmup());
//                });
//
//        template.setId(workoutTemplateId);
//        return template;
//
//    }

}
