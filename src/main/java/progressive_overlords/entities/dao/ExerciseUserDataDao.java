package progressive_overlords.entities.dao;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseUserDataDao {

    private double maxWeight;
    private double avgReps;
    private double erp;
    private List<ErpValue> values;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class ErpValue {
        private double erp;
        private String date;
    }
}


