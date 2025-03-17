package progressive_overlords.entities.dao;

import lombok.*;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;

import java.util.*;
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
    private List<String> tags;
    private String unparsedTags;
    private List<ExerciseDao> exercises = new ArrayList<>();
    private String createdAt;
    private String updatedAt;
    private String startDate;
    private String endDate;
    private boolean isTemplate;
    private Integer templateId;

    public void setExercisesDaoFromSetsDto(List<SetDto> sets)  {
        if (sets == null || sets.isEmpty()) {
            throw new BadRequestException("No sets were provided.");
        }

        HashMap<Integer, ExerciseDao> exerciseMap = new HashMap<>();

        try {
            for (SetDto set : sets) {
                if (exerciseMap.containsKey(set.getExerciseId())) {
                    SetDao newSet = SetDao.builder().exerciseNum(exerciseMap.get(set.getExerciseId()).getSets().get(0).getExerciseNum()).setNum(exerciseMap.get(set.getExerciseId()).getSets().size()).exerciseId(set.getExerciseId()).reps(set.getReps()).weight(set.getWeight()).warmup(set.isWarmup()).build();
                    exerciseMap.get(set.getExerciseId()).getSets().add(newSet);
                } else {
                    ExerciseDao newExercise = ExerciseDao.builder().exerciseNum(exerciseMap.size()).exerciseId(set.getExerciseId()).sets(new ArrayList<>()).build();
                    SetDao newSet = SetDao.builder().exerciseNum(exerciseMap.size()).setNum(0).exerciseId(set.getExerciseId()).reps(set.getReps()).weight(set.getWeight()).warmup(set.isWarmup()).build();
                    newExercise.getSets().add(newSet);
                    exerciseMap.put(set.getExerciseId(), newExercise);
                }
            }
            this.exercises = new ArrayList<>(exerciseMap.values());
            this.exercises.sort(Comparator.comparingInt(ExerciseDao::getExerciseNum));
        } catch (Exception e) {
            throw new BadRequestException("There is a mismatch between the number of exercises and sets selected. Please review your template.");
        }
    }

    public List<SetDao> getFlatSetsList () {
        List<SetDao> sets = new ArrayList<>();
        for (ExerciseDao exerciseDao : this.exercises) {
            for (SetDao set : exerciseDao.getSets()) {
                sets.add(set);
            }
        }
        return sets;
    }

    public void setExercisesDaoFromSetsDao(List<SetDao> sets)  {
        if (sets == null || sets.isEmpty()) {
            this.exercises = null;
            return;
//            throw new BadRequestException("No sets were provided.");
        }
        sets.sort(Comparator.comparingInt(SetDao::getExerciseNum)
                .thenComparingInt(SetDao::getSetNum));

        Map<Integer, ExerciseDao> exerciseMap = new HashMap<>();
        try {
            for (SetDao set : sets) {
                ExerciseDao exerciseDao = exerciseMap.computeIfAbsent(set.getExerciseNum(), num -> ExerciseDao.builder().exerciseNum(set.getExerciseNum()).exerciseId(set.getExerciseId()).sets(new ArrayList<>()).build());
                exerciseDao.getSets().add(set);
            }
            this.exercises = new ArrayList<>(exerciseMap.values());
            this.exercises.sort(Comparator.comparingInt(ExerciseDao::getExerciseNum));
        } catch (Exception e) {
            throw new BadRequestException("There is a mismatch between the number of exercises and sets selected. Please review your template.");
        }
    }

    public void parseTags(String tags) {
        if (tags == null || tags.trim().isEmpty()) {
            return;
        }

        this.tags = Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void mergeSetsWithTemplate(WorkoutDao template) {

        HashSet<String> alreadyFoundSets = new HashSet<>();
        List<SetDao> result = new ArrayList<>();

        if (this.exercises != null) {
            List<SetDao> sets = this.getFlatSetsList();
            for (SetDao set : sets) {
                set.setCompleted(true);
                alreadyFoundSets.add(set.getExerciseNum() + "-" + set.getSetNum());
                result.add(set);
            }
        }

        if (template.getExercises() != null) {
            List<SetDao> templateSetsList = template.getFlatSetsList();
            for (SetDao set : templateSetsList) {
                set.setCompleted(false);
                if (!alreadyFoundSets.contains(set.getExerciseNum() + "-" + set.getSetNum())) {
                    alreadyFoundSets.add(set.getExerciseNum() + "-" + set.getSetNum());
                    result.add(set);
                }
            }
        }

        this.setExercisesDaoFromSetsDao(result);
    }
}
