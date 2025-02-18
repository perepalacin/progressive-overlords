package progressive_overlords.entities.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateDto {
    @NotBlank(message="Template name is required")
    @Size(min= 1, max = 50, message="The template name must be between 1 and 45 characters.")
    private String name;
    private String description;
    private String color;
    private String bodyPart;
    private String tags;
    private List<Integer> exercisesId;
    private List<Integer> sets;
    private List<Integer> reps;
}
