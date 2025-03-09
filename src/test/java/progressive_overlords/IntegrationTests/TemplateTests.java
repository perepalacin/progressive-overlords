package progressive_overlords.IntegrationTests;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.WorkoutService;

@RequiredArgsConstructor
public class TemplateTests {

    private final WorkoutService sut;

    @Test
    public void TemplateSucceeds () {
        WorkoutDto template = WorkoutDto.builder()
                .name("Test template")
                .description("A sample template test")
                .color("#FF00FF")
                .bodyPart("arms")
                .unparsedTags("strength,endurance")
                .build();

        WorkoutDao result = sut.createTemplate(template);
        Assertions.assertEquals(result.getName(), template.getName());
        Assertions.assertEquals(result.getDescription(), template.getDescription());
        Assertions.assertEquals(result.getColor(), template.getColor());
        Assertions.assertEquals(result.getBodyPart(), template.getBodyPart());
        Assertions.assertEquals(result.getUnparsedTags(), template.getUnparsedTags()s);

    }

}
