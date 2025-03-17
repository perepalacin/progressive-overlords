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
                    "INSERT INTO workout_exercises (workout_id, exercise_id, set_num, reps, weight, annotation, user_id, exercise_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, setDao.getWorkoutId());
            ps.setInt(2, setDao.getExerciseId());
            ps.setInt(3, setDao.getSetNum());
            ps.setFloat(4, setDao.getReps());
            ps.setFloat(5, setDao.getWeight());
            ps.setString(6, setDao.getAnnotation());
            ps.setObject(7, userId);
            ps.setInt(8, setDao.getExerciseNum());
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
            SET updated_at = CURRENT_TIMESTAMP, reps = ?, weight = ?, annotation = ?
            WHERE id = ? AND user_id = ?
        """;
        jdbcTemplate.update(updateWorkoutSQL,
                setDao.getReps(),
                setDao.getWeight(),
                setDao.getAnnotation(),
                setDao.getId(),
                userId
        );

        return setDao;
    }

    public void deleteSet(int setId) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return;
            // TODO: Throw an exception to unauthorized request maybe?
        }

        String deleteSetsSQL = """
            DELETE FROM workout_exercises
            WHERE id = ? AND user_id = ?
        """;
        jdbcTemplate.update(deleteSetsSQL, setId, userId);
    }
}
