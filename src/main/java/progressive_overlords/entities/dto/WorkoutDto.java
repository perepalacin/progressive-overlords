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
    private List<Float> reps;
    private List<Integer> setNums;
    private List<Float> weights;
    private List<Boolean> warmups;
    private List<Integer> exercisesId;
    private Integer templateId;
    private boolean isTemplate;
}
