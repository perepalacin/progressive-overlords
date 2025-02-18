package progressive_overlords.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import progressive_overlords.entities.dao.TemplateDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemplateRowMapper implements RowMapper<TemplateDao>  {
    @Override
    public TemplateDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        TemplateDao templateDao = new TemplateDao();
        templateDao.setName(rs.getString("name"));
        templateDao.setDescription(rs.getString("description"));
        templateDao.setColor(rs.getString("color"));
        templateDao.setBodyPart(rs.getString("body_part"));
         templateDao.setTags(parseTags(rs.getString("tags")));
        return templateDao;
    }

    private List<String> parseTags(String tags) {
        if (tags == null || tags.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}

