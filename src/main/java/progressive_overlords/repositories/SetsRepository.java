package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dto.SetDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SetsRepository {

    private final JdbcTemplate jdbcTemplate;

    public SetDao getById(int id) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            //TODO: Throw invalid!;
        }

    String sqlStatement = """
            SELECT
                id,
                workout_id,
                exercise_id,
                exercise_num,
                set_num,
                reps,
                weight,
                is_warmup,
                user_id
            FROM workout_exercises we
            WHERE we.id = ? AND we.user_id = ?
        """;

        List<SetDao> setList = jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
        SetDao set = SetDao.builder()
                .id(rs.getInt("id"))
                .workoutId(rs.getInt("workout_id"))
                .exerciseId(rs.getInt("exercise_id"))
                .exerciseNum(rs.getInt("exercise_num"))
                .setNum(rs.getInt("set_num"))
                .reps(rs.getFloat("reps"))
                .weight(rs.getFloat("weight"))
                .warmup(rs.getBoolean("is_warmup"))
                .build();
            return set;
        }, id, userId);
        return setList.isEmpty() ? null : setList.get(0);
}

    public SetDao createSet(SetDao newSet) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            //TODO: Throw invalid!;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workout_exercises (workout_id, exercise_id, exercise_num, set_num, reps, weight, is_warmup, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, newSet.getWorkoutId());
            ps.setInt(2, newSet.getExerciseId());
            ps.setInt(3, newSet.getExerciseNum());
            ps.setInt(4, newSet.getSetNum());
            ps.setDouble(5, newSet.getReps());
            ps.setDouble(6, newSet.getWeight());
            ps.setBoolean(7, newSet.isWarmup());
            ps.setObject(8, userId);
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null || !keys.containsKey("id")) {
            throw new RuntimeException("Failed to retrieve generated workout Id");
        }
        newSet.setId(((Number) keys.get("id")).intValue());
        newSet.setCompleted(true);
        return newSet;
    }

    public SetDao updateSet(SetDao newSet) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            //TODO: Throw unvalid!;
        }

        String sqlStatement = """
                UPDATE workout_exercises SET is_warmup = ?, reps = ?, weight = ?
                WHERE id = ? AND user_id = ?
                """;
        jdbcTemplate.update(sqlStatement,
                newSet.isWarmup(),
                newSet.getReps(),
                newSet.getWeight(),
                newSet.getId(),
                userId
        );

        newSet.setCompleted(true);
        return newSet;
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

}
