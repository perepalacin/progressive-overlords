package progressive_overlords.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FriendsRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PublicUserDao> getRandomUsersSuggestion (int number) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String sql = """
                SELECT u.*
                FROM progressive_overlords.users u
                LEFT JOIN progressive_overlords.friends f
                  ON f.follower_user_id = ? AND f.following_user_id = u.id
                WHERE f.id IS NULL
                  AND u.id != ?
                ORDER BY RANDOM()
                LIMIT ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return PublicUserDao.builder()
                    .userId((UUID) rs.getObject("id"))
                    .username(rs.getString("username"))
                    .build();
        }, userId, userId, number);
    }

    public List<PublicUserDao> getUserFriendList() {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sql = """
                SELECT u.*
                FROM progressive_overlords.users u
                LEFT JOIN progressive_overlords.friends f
                  ON f.follower_user_id = u.id
                WHERE u.id = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return PublicUserDao.builder()
                    .userId((UUID) rs.getObject("u.id"))
                    .username(rs.getString("u.username"))
                    .build();
        }, userId);
    }

    public boolean followUser (UUID userId) {
        UUID currentUserId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO friends (follower_user_id, following_user_id) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, currentUserId);
                ps.setObject(2, userId);
                return ps;
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unFollowUser (UUID userId) {
        UUID currentUserId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM friends WHERE follower_user_id = ? AND following_user_id = ?");
                ps.setObject(1, currentUserId);
                ps.setObject(2, userId);
                return ps;
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
