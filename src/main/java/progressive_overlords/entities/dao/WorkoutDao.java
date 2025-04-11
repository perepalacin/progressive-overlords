package progressive_overlords.entities.dao;

import lombok.*;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.utils.TimeDiffHelper;

import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDao {
    private int id;
    private String name;
    private String description;
    private String color;
    private List<String> tags;
    private String unparsedTags;
    private List<WorkoutExerciseDao> exercises = new ArrayList<>();
    private String createdAt;
    private String updatedAt;
    private String startDate;
    private String endDate;
    private boolean isTemplate;
    private Integer templateId;
    private AggregatedWorkoutDataDao aggregatedWorkoutData;


    public List<SetDao> getFlatSetsList () {
        List<SetDao> sets = new ArrayList<>();
        for (WorkoutExerciseDao exerciseDao : this.exercises) {
            sets.addAll(exerciseDao.getSets());
        }
        return sets;
    }

    public void generateWorkoutAggregatedData () {
        double totalWeight = 0;
        for (WorkoutExerciseDao exerciseDao : this.getExercises()) {
            for (SetDao set : exerciseDao.getSets()) {
                totalWeight += set.getReps() * set.getWeight();
            }
        }
        this.setAggregatedWorkoutData(AggregatedWorkoutDataDao.builder()
                .volume(totalWeight)
                .duration(TimeDiffHelper.getTimeDifference(this.startDate,this.endDate))
                .build());
    }
}
