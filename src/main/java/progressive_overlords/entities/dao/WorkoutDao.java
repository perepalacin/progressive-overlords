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

//    public void mergeSetsWithTemplate(WorkoutDao template) {
//
//        HashSet<String> alreadyFoundSets = new HashSet<>();
//        List<SetDao> result = new ArrayList<>();
//
//        if (this.exercises != null) {
//            List<SetDao> sets = this.getFlatSetsList();
//            for (SetDao set : sets) {
//                set.setCompleted(true);
//                alreadyFoundSets.add(set.getExerciseNum() + "-" + set.getSetNum());
//                result.add(set);
//            }
//        }
//
//        if (template.getExercises() != null) {
//            List<SetDao> templateSetsList = template.getFlatSetsList();
//            for (SetDao set : templateSetsList) {
//                set.setCompleted(false);
//                if (!alreadyFoundSets.contains(set.getExerciseNum() + "-" + set.getSetNum())) {
//                    alreadyFoundSets.add(set.getExerciseNum() + "-" + set.getSetNum());
//                    result.add(set);
//                }
//            }
//        }
//
//        this.setExercisesDaoFromSetsDao(result);
//    }


}
