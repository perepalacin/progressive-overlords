package progressive_overlords.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WorkoutExerciseDao {
    private ExerciseDao exercise;
    private int exerciseId;
    private int exerciseNum;
    private List<SetDao> sets;
    private int setsCount; // Optional flag for summarized workout data
}
