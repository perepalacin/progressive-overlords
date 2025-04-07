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
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SetsRepository {

    private final JdbcTemplate jdbcTemplate;

    public

    public SetDao createSet(SetDao newSet) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            //TODO: Throw unvalid!;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO workout_exercises (workout_id, exercise_id, exercise_num, set_num, reps, weight, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, newSet.getWorkoutId());
            ps.setInt(2, newSet.getExerciseId());
            ps.setInt(3, newSet.getExerciseNum());
            ps.setInt(4, newSet.getSetNum());
            ps.setDouble(5, newSet.getReps());
            ps.setDouble(6, newSet.getWeight());
            ps.setObject(7, userId);
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
}
