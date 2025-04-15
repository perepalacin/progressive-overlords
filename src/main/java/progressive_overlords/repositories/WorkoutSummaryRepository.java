package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dao.WorkoutSummaryDao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

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

        String insertExercisesSQL = "INSERT INTO workout_exercises_summary (workout_id, exercise_id, sets, exercise_num) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(insertExercisesSQL, workoutSummaryDao.getWorkoutExercises(), workoutSummaryDao.getWorkoutExercises().size(),
                (ps, exercise) -> {
                    ps.setInt(1, workoutSummaryDao.getWorkoutId());
                    ps.setInt(2, exercise.getExerciseId());
                    ps.setInt(3, exercise.getSets().size());
                    ps.setInt(4, exercise.getExerciseNum());
        });
    }

    public List<WorkoutSummaryDao> getFriendsActivity (int page) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        //TODO: Add workout name!
        String sqlStatement = """
                WITH limited_workouts AS (
                    SELECT workout_id
                    FROM workouts_summary ws
                    JOIN friends f ON f.following_user_id = ws.user_id
                    WHERE f.follower_user_id = '382efc6f-03e8-4a58-9882-c2b9daa00830'
                    GROUP BY workout_id
                    ORDER BY MAX(ws.started_at) DESC
                    LIMIT ?
                )
                SELECT
                    w.name AS name,
                    ws.workout_id AS workout_id,
                    ws.duration AS duration,
                    ws.volume AS volume,
                    ws.started_at AS started_at,
                    wes.exercise_id AS exercise_id,
                    wes.sets AS sets,
                    wes.exercise_num AS exercise_num,
                    u.id AS id,
                    u.username AS username
                FROM workouts_summary ws
                JOIN limited_workouts lw ON lw.workout_id = ws.workout_id
                LEFT JOIN workout_exercises_summary wes ON wes.workout_id = ws.workout_id
                LEFT JOIN friends f ON f.following_user_id = ws.user_id
                LEFT JOIN users u ON u.id = f.following_user_id
                LEFT JOIN workouts w ON w.id = ws.workout_id
                WHERE f.follower_user_id = ?
                ORDER BY ws.started_at DESC
                """;

        HashSet<WorkoutSummaryDao> result = new HashSet<>();

        jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            int workoutId = rs.getInt("workout_id");
            boolean found = false;
            for (WorkoutSummaryDao workoutSummary : result) {
                if (workoutSummary.getWorkoutId() == workoutId) {
                    System.out.println("found existing workout" + workoutId + rs.getInt("exercise_num"));
                    found = true;
                    workoutSummary.getWorkoutExercises().add(
                            WorkoutExerciseDao.builder()
                                    .setsCount(rs.getInt("sets"))
                                    .exerciseNum(rs.getInt("exercise_num"))
                                    .exerciseId(rs.getInt("exercise_id"))
                                    .build()
                    );
                }
            }
            if (!found) {
                System.out.println("Workout not found, creating a ne one" + workoutId + rs.getInt("exercise_num"));
                WorkoutSummaryDao newWorkout =  WorkoutSummaryDao.builder()
                        .name(rs.getString("name"))
                        .publicUserDao(PublicUserDao.builder().userId((UUID) rs.getObject("id")).username(rs.getString("username")).build())
                        .workoutId(rs.getInt("workout_id"))
                        .startDate(rs.getString("started_at"))
                        .volume(rs.getDouble("volume"))
                        .workoutExercises(new ArrayList<>())
                        .duration(rs.getInt("duration"))
                        .build();
                newWorkout.getWorkoutExercises().add(
                        WorkoutExerciseDao.builder()
                                .setsCount(rs.getInt("sets"))
                                .exerciseId(rs.getInt("exercise_id"))
                                .exerciseNum(rs.getInt("exercise_num"))
                                .build());
                result.add(newWorkout);
            }
             return rs;
        }, 3*(page+1), userId);

        return result.stream().toList();
    }

}
