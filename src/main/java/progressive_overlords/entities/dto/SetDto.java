package progressive_overlords.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SetDto {
    private int exerciseId;
    private float reps;
    private float weight;
    private boolean warmup;
}
