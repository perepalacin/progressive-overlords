package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.UserDao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserDao findByUsername (String username) {
        String sqlStatement = "SELECT id, username, password FROM users WHERE username = ?";
        List<UserDao> user = jdbcTemplate.query(
                sqlStatement,
                new Object[]{username},
                (rs, rowNum) -> UserDao.builder().id(UUID.fromString(rs.getString("id"))).username(rs.getString("username")).password(rs.getString("password")).build()
        );
        return user.get(0);
    }

    public UserDao findById (UUID userId) {
        String sqlStatement = "SELECT id, username, password, weight_units FROM users WHERE id = ?";
        List<UserDao> user = jdbcTemplate.query(
                sqlStatement,
                new Object[]{userId},
                (rs, rowNum) -> UserDao.builder().id(UUID.fromString(rs.getString("id"))).username(rs.getString("username")).password(rs.getString("password")).weightUnits(rs.getString("weight_units")).build()
        );
        return user.get(0);
    }

}
