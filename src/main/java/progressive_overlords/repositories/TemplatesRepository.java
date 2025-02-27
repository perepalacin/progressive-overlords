package progressive_overlords.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TemplatesRepository {

//    public TemplateDao saveTemplate(TemplateDao template, UUID userId) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "INSERT INTO workout_templates (name, description, color, body_part, tags, user_id) VALUES (?, ?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, template.getName());
//            ps.setString(2, template.getDescription() != null ? template.getDescription() : "");
//            ps.setString(3, template.getColor() != null ? template.getColor() : "#CD4945");
//            ps.setString(4, template.getBodyPart());
//            ps.setString(5, template.getTags() != null ? template.getUnparsedTags() : "");
//            ps.setObject(6, userId);
//            return ps;
//        }, keyHolder);
//
//        Map<String, Object> keys = keyHolder.getKeys();
//        if (keys == null || !keys.containsKey("id")) {
//            throw new RuntimeException("Failed to retrieve generated workout template ID");
//        }
//
//        int workoutTemplateId = ((Number) keys.get("id")).intValue(); // Correct casting
//
//        String insertExercisesSQL = "INSERT INTO workout_template_exercises (workout_template_id, exercise_id, set_num, weight, reps) VALUES (?, ?, ?, ?, ?)";
//
//        List<SetDao> sets = template.getSets();
//        jdbcTemplate.batchUpdate(insertExercisesSQL, sets, sets.size(),
//                (ps, exercise) -> {
//                    ps.setInt(1, workoutTemplateId);
//                    ps.setInt(2, exercise.getExerciseId());
//                    ps.setInt(3, exercise.getSetNum());
//                    ps.setInt(4, exercise.getWeight());
//                    ps.setInt(5, exercise.getReps());
//                });
//
//        template.setId(workoutTemplateId);
//        return template;
//    }

//    private final JdbcTemplate jdbcTemplate;
//
//    public TemplateDao getByTemplateId(int templateId, UUID userId) {
//        String sql = """
//            SELECT
//                wt.id,
//                wt.name,
//                wt.description,
//                wt.color,
//                wt.body_part,
//                wt.tags,
//                COALESCE(json_agg(
//                    json_build_object(
//                        'exerciseId', wte.exercise_id,
//                        'setNum', wte.set_num,
//                        'weight', wte.weight,
//                        'reps', wte.reps
//                    )
//                ) FILTER (WHERE wte.exercise_id IS NOT NULL), '[]') AS exercises
//            FROM workout_templates wt
//            LEFT JOIN workout_template_exercises wte ON wt.id = wte.workout_template_id
//            WHERE wt.user_id = ? AND wt.id = ?
//            GROUP BY wt.id
//        """;
//        List<TemplateDao> templateList = jdbcTemplate.query(sql, new Object[]{userId, templateId}, (rs, rowNum) -> {
//            String exercisesJson = rs.getString("exercises");
//
//            List<SetDao> setList = null;
//            try {
//                setList = new ObjectMapper().readValue(
//                        exercisesJson,
//                        new com.fasterxml.jackson.core.type.TypeReference<>() {}
//                );
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//
//            List<Integer> exercisesId = setList.stream().map(SetDao::getExerciseId).toList();
//            List<Integer> sets = setList.stream().map(SetDao::getSetNum).toList();
//            List<Integer> reps = setList.stream().map(SetDao::getReps).toList();
//
//            return new TemplateDao(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getString("description"),
//                    rs.getString("color"),
//                    rs.getString("body_part"),
//                    rs.getString("tags"),
//                    exercisesId,
//                    sets,
//                    reps
//            );
//        });
//
//        return templateList.isEmpty() ? null : templateList.get(0);
//    }
//
//    public TemplateDao editTemplate(int templateId, TemplateDao template, UUID userId) {
//        String updateTemplateSQL = """
//            UPDATE workout_templates
//            SET name = ?, description = ?, color = ?, body_part = ?, tags = ?, updated_at = CURRENT_TIMESTAMP
//            WHERE id = ? AND user_id = ?
//        """;
//
//        jdbcTemplate.update(updateTemplateSQL,
//                template.getName(),
//                template.getDescription() != null ? template.getDescription() : "",
//                template.getColor() != null ? template.getColor() : "#CD4945",
//                template.getBodyPart(),
//                template.getTags() != null ? template.getUnparsedTags() : "",
//                templateId,
//                userId
//        );
//
//        String deleteExercisesSQL = """
//            DELETE FROM workout_template_exercises
//            WHERE workout_template_id = ?
//        """;
//        jdbcTemplate.update(deleteExercisesSQL, templateId);
//
//        List<Integer> updatedExerciseIds = template.getSets().stream()
//                .map(SetDao::getExerciseId)
//                .toList();
//
//
//        String insertExercisesSQL = "INSERT INTO workout_template_exercises (workout_template_id, exercise_id, set_num, weight, reps) VALUES (?, ?, ?, ?, ?)";
//
//        jdbcTemplate.batchUpdate(insertExercisesSQL, template.getSets(), template.getSets().size(),
//                (ps, exercise) -> {
//                    ps.setInt(1, templateId);
//                    ps.setInt(2, exercise.getExerciseId());
//                    ps.setInt(3, exercise.getSetNum());
//                    ps.setInt(4, exercise.getWeight());
//                    ps.setInt(5, exercise.getReps());
//                });
//
//        template.setId(templateId);
//        return template;
//    }
//
//    public boolean deleteTemplate(int templateId, UUID userId) {
//
//        try {
//            String preparedStatement = """
//                DELETE FROM workout_templates
//                WHERE id = ?
//                AND user_id = ?
//            """;
//
//            jdbcTemplate.update(preparedStatement, templateId, userId);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//

}
