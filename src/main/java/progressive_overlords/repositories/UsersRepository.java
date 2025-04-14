package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.entities.dao.UserDao;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserDao findByUsername (String username) {
        String sqlStatement = "SELECT id, username, password FROM users WHERE username = ?";
        List<UserDao> userList = jdbcTemplate.query(
                sqlStatement,
                (rs, rowNum) -> UserDao.builder().id(UUID.fromString(rs.getString("id"))).username(rs.getString("username")).password(rs.getString("password")).build(),
                username
        );
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public UserDao findById (UUID userId) {
        String sqlStatement = "SELECT id, username, password, weight_units FROM users WHERE id = ?";
        List<UserDao> userList = jdbcTemplate.query(
                sqlStatement,
                (rs, rowNum) -> UserDao.builder().id(UUID.fromString(rs.getString("id"))).username(rs.getString("username")).password(rs.getString("password")).weightUnits(rs.getString("weight_units")).build(),
                userId
        );
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public void save(UserDao user) {
        String sqlStatement = "INSERT INTO users ( username, password, weight_units, enabled) VALUES (?, ?, 'kg', TRUE)";
        jdbcTemplate.update(sqlStatement,
                user.getUsername(),
                user.getPassword()
        );
    }

    public PublicUserDao getOwnPublicDetails () {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            return null;
        }

        String sqlStatement = """
                SELECT u.id, u.username, agg_u.followers, agg_u.following, agg_u.workouts
                FROM users as u
                LEFT JOIN aggregated_user_data as agg_u
                    ON agg_u.user_id = u.id
                WHERE u.id = ?
                LIMIT 1
                """;

        List<PublicUserDao> userDetails = jdbcTemplate.query(
                sqlStatement,
                (rs, rowNum) -> PublicUserDao.builder()
                        .userId(UUID.fromString(rs.getString("id")))
                        .username(rs.getString("username"))
                        .followers(rs.getInt("followers"))
                        .following(rs.getInt("following"))
                        .workouts(rs.getInt("workouts"))
                        .build(),
                userId
        );

        return userDetails.isEmpty() ? null : userDetails.get(0);
    }

}