package progressive_overlords.entities.dao;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetDao {
    private int id;
    private int setNum;
    private int exerciseId;
    private int reps;
    private int weight;
    private String annotation;
}
