package progressive_overlords.entities.dao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetDao {
    private int id;
    private int workoutId;
    private int exerciseId;
    private int setNum;
    private float reps;
    private float weight;
    private String annotation;
}
