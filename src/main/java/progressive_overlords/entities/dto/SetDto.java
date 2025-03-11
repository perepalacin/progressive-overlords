package progressive_overlords.entities.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SetDto {
    private int setNum;
    private int exerciseId;
    private float reps;
    private float weight;
    private boolean warmup;
}
