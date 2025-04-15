package progressive_overlords.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WorkoutSummaryDao {
    private String name;
    private int id;
    private int workoutId;
    private PublicUserDao publicUserDao;
    private List<WorkoutExerciseDao> workoutExercises;
    private long duration;
    private double volume;
    private String startDate;
}
