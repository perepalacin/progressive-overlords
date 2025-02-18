package progressive_overlords.entities.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SetDao {
    private int id;
    private int exerciseId;
    private int reps;
    private int weight;
    private String annotation;
}
