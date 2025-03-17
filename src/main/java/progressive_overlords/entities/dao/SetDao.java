package progressive_overlords.entities.dao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetDao {
    private int id;
    private int exerciseNum;
    private int workoutId;
    private int exerciseId;
    private int setNum;
    private boolean warmup;
    private float reps;
    private float weight;
    private String annotation;
    private boolean isCompleted;
}
