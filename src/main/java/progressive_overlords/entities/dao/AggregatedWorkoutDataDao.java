package progressive_overlords.entities.dao;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AggregatedWorkoutDataDao {
    private double volume;
    private String duration;
}
