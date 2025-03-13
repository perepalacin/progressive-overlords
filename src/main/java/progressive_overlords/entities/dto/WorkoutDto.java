package progressive_overlords.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WorkoutDto {
    private Integer id;
    @NotBlank(message="Template name is required")
    @Size(min= 1, max = 50, message="The template name must be between 1 and 45 characters.")
    private String name;
    private String description;
    private String color;
    private String unparsedTags;
    private List<SetDto> sets;
    private Integer templateId;
    private Boolean isTemplate;
}
