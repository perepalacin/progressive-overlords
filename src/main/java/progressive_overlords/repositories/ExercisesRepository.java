package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.entities.dao.WorkoutDao;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ExercisesRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<ExerciseDao> getAll() {
        String sqlStatement = "SELECT id, name, description FROM exercises ORDER BY name";
        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            ExerciseDao exerciseDao = ExerciseDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .thumbnail("https://cdn.iconscout.com/icon/premium/png-256-thumb/quadriceps-8692513-7077034.png?f=webp&w=256")
                    .build();
            return exerciseDao;
        });
    }

    public List<ExerciseDao> getWithoutQuery(int page) {

        String sqlStatement = "SELECT id, name, description FROM exercises ORDER BY name OFFSET ? LIMIT ?";
        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            ExerciseDao exerciseDao = ExerciseDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .thumbnail("https://cdn.iconscout.com/icon/premium/png-256-thumb/quadriceps-8692513-7077034.png?f=webp&w=256")
                    .build();
            return exerciseDao;
        }, page*20, 20);
    }

    public List<ExerciseDao> getWithQuery(int page, String query) {

        String sqlStatement = "SELECT id, name, description FROM exercises WHERE name like '%?%' ORDER BY name OFFSET ? LIMIT ?";
        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            ExerciseDao exerciseDao = ExerciseDao.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .thumbnail("https://cdn.iconscout.com/icon/premium/png-256-thumb/quadriceps-8692513-7077034.png?f=webp&w=256")
                    .build();
            return exerciseDao;
        }, query, page*20, 20);
    }

}