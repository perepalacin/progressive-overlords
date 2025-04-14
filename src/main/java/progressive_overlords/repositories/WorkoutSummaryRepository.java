package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutSummaryDao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WorkoutSummaryRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save (WorkoutSummaryDao workoutSummaryDao) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workouts_summary (workout_id, duration, volume, user_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, workoutSummaryDao.getWorkoutId());
            ps.setDouble(2, workoutSummaryDao.getDuration());
            ps.setDouble(3, workoutSummaryDao.getVolume());
            ps.setObject(4, workoutSummaryDao.getPublicUserDao().getUserId());
            return ps;
        });

        String insertExercisesSQL = "INSERT INTO workout_exercises_summary (workout_id, exercise_id, sets) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(insertExercisesSQL, workoutSummaryDao.getWorkoutExercises(), workoutSummaryDao.getWorkoutExercises().size(),
                (ps, exercise) -> {
                    ps.setInt(1, workoutSummaryDao.getWorkoutId());
                    ps.setInt(2, exercise.getExerciseId());
                    ps.setInt(3, exercise.getSets().size());
        });
    }

//    public List<WorkoutSummaryDao> getFriendsActivity (int page) {
//        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userId == null) {
//            return null;
//        }
//        String sqlStatement = """
//                SELECT
//                    *
//                FROM
//                    workouts_summary as ws
//                LEFT JOIN
//                    users as u
//
//                """;
////        String sqlStatement = "SELECT * FROM workouts_summary as ws WHERE template_id IS NULL AND is_template = true AND user_id = ? ORDER BY name";
//        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
//            return WorkoutDao.builder()
//                    .id(rs.getInt("id"))
//                    .name(rs.getString("name"))
//                    .description(rs.getString("description"))
//                    .build();
//        }, userId);
//    }

}
