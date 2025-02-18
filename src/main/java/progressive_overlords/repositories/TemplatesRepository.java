package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dto.TemplateDto;
import progressive_overlords.mappers.TemplateRowMapper;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TemplatesRepository {

    private final JdbcTemplate jdbcTemplate;

    public TemplateDao saveTemplate(TemplateDto template, UUID userId) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO workout_templates (name, description, color, body_part, tags, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?) RETURNING *",
                new Object[]{
                        template.getName(),
                        template.getDescription() != null ? template.getDescription() : "",
                        template.getColor() != null ? template.getColor() : "#CD4945",
                        template.getBodyPart(),
                        template.getTags() != null ? template.getTags() : "",
                        userId
                },
                (rs, rowNum) -> new TemplateRowMapper().mapRow(rs, rowNum)
        );
    }

}
