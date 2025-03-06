package progressive_overlords.entities.dao;

import lombok.*;
import progressive_overlords.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private String bodyPart;
    private List<String> tags;
    private String unparsedTags;
    private List<SetDao> sets;
    private String createdAt;
    private String updatedAt;
    private String startDate;
    private String endDate;
    private boolean isTemplate;
    private Integer templateId;

    public void parseSetsList(List<Integer> exercisesId, List<Integer> sets, List<Float> reps)  {
        List<SetDao> setList = new ArrayList<>();

        try {
            for (int i = 0; i < sets.size(); i++) {
                SetDao newSet = SetDao.builder().id(i).exerciseId(exercisesId.get(i)).reps(reps.get(i)).build();
                setList.add(newSet);
            }
        } catch (Exception e) {
            throw new BadRequestException("There is a mismatch between the number of exercises and sets selected. Please review your template.");
        }

        this.sets = setList;
    }

    public void parseTags(String tags) {
        if (tags == null || tags.trim().isEmpty()) {
            this.tags = Collections.emptyList();
        }

        this.tags = Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void mergeSetsWithTemplate(WorkoutDao template) {
        List<SetDao> sets = this.getSets();
        int workoutCompletedSets = sets.size();
        for (int i = 0; i < template.getSets().size(); i++) {
            if (i >= workoutCompletedSets) {
                sets.add(template.getSets().get(i));
            }
        }
        this.setSets(sets);
    }
}
