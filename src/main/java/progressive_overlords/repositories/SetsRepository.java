package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.SetDao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SetsRepository {

    private final JdbcTemplate jdbcTemplate;

    public SetDao createSet(SetDao setDao) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
            // TODO: Throw an exception to unauthorized request maybe?
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, reps, weight, annotation, user_id, exercise_num, is_warmup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, setDao.getWorkoutId());
            ps.setInt(2, setDao.getExerciseId());
            ps.setInt(3, setDao.getSetNum());
            ps.setFloat(4, setDao.getReps());
            ps.setFloat(5, setDao.getWeight());
            ps.setString(6, setDao.getAnnotation());
            ps.setObject(7, userId);
            ps.setInt(8, setDao.getExerciseNum());
            ps.setBoolean(9, setDao.isWarmup());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout Id");
        }

        setDao.setId(((Number) keys.get("id")).intValue());
        return setDao;
    }

    public SetDao editSet(SetDao setDao) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userId == null) {
            return null;
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String updateWorkoutSQL = """
            UPDATE workout_exercises
            SET updated_at = CURRENT_TIMESTAMP, reps = ?, weight = ?, annotation = ?, is_warmup = ?
            WHERE id = ? AND user_id = ?
        """;
        jdbcTemplate.update(updateWorkoutSQL,
                setDao.getReps(),
                setDao.getWeight(),
                setDao.getAnnotation(),
                setDao.isWarmup(),
                setDao.getId(),
                userId
        );

        return setDao;
    }

    public void editSetList(List<SetDao> sets) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userId == null) {
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String updateSetsSQL = """
            UPDATE workout_exercises
            SET updated_at = CURRENT_TIMESTAMP, set_num = ?
            WHERE id = ? AND user_id = ?
        """;

        jdbcTemplate.batchUpdate(updateSetsSQL, sets, sets.size(),
                (ps, set) -> {
                    ps.setInt(1, set.getSetNum());
                    ps.setInt(2, set.getId());
                    ps.setObject(3, userId);
        });
    }

    public void deleteSet(int setId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String deleteSetsSQL = """
            DELETE FROM workout_exercises
            WHERE id = ? AND user_id = ?
        """;
        jdbcTemplate.update(deleteSetsSQL, setId, userId);
    }

    public List<SetDao> getListBySetId (int setId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
            // TODO: Throw an exception to unauthorized request maybe?
        }
        String sqlStatement = """
                SELECT
                    we.id,
                    we.workout_id,
                    we.exercise_id,
                    we.exercise_num,
                    we.set_num,
                    we.reps,
                    we.weight,
                    we.annotation,
                    we.is_warmup
                FROM
                    progressive_overlords.workout_exercises cs
                LEFT JOIN
                    progressive_overlords.workout_exercises we
                    ON cs.workout_id = we.workout_id
                    AND cs.exercise_id = we.exercise_id
                WHERE
                    cs.id = ?
                AND
                    cs.user_id = ?
                order by
                	cs.set_num;
                """;

        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
             SetDao set = SetDao.builder()
                    .id(rs.getInt("id"))
                    .workoutId(rs.getInt("workout_id"))
                    .exerciseId(rs.getInt("exercise_id"))
                    .setNum(rs.getInt("set_num"))
                    .reps(rs.getFloat("reps"))
                    .weight(rs.getFloat("weight"))
                    .warmup(rs.getBoolean("is_warmup"))
                    .annotation(rs.getString("annotation"))
                    .isCompleted(true)
                    .build();
             return set;
        }, setId, userId);
    }

    public void deleteExerciseFromWorkout (int workoutId, int exerciseId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String deleteExerciseSQL = """
            DELETE FROM workout_exercises
            WHERE exercise_id = ? AND workout_id = ? AND user_id = ?
        """;
        jdbcTemplate.update(deleteExerciseSQL, exerciseId, workoutId, userId);
    }
}
