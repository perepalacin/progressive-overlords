package progressive_overlords.entities.dao;

import lombok.*;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;

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


    public List<SetDao> getFlatSetsList () {
        List<SetDao> sets = new ArrayList<>();
        for (WorkoutExerciseDao exerciseDao : this.exercises) {
            sets.addAll(exerciseDao.getSets());
        }
        return sets;
    }
}
