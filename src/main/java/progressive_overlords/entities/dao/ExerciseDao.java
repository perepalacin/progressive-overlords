package progressive_overlords.entities.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ExerciseDao {
    private int exerciseId;
    private int exerciseNum;
    private List<SetDao> sets;
}
