package progressive_overlords.mappers;

import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dto.WorkoutDto;

import java.util.ArrayList;
import java.util.List;

public class WorkoutMapper {

    public static WorkoutDao mapDtoToDao (WorkoutDto workoutDto) throws RuntimeException {

        WorkoutDao workout = WorkoutDao.builder().name(workoutDto.getName()).description(workoutDto.getDescription()).isTemplate(workoutDto.isTemplate()).templateId(workoutDto.getTemplateId()).build();

        if (workoutDto.getReps() == null || workoutDto.getWarmups() == null || workoutDto.getWeights() == null || workoutDto.getSetNums() == null || workoutDto.getExercisesId() == null) {
            throw new RuntimeException("Workout not formatted properly");
        }

        if ((workoutDto.getReps().size() != workoutDto.getWeights().size()) || (workoutDto.getWeights().size() != workoutDto.getWarmups().size()) || (workoutDto.getWeights().size() != workoutDto.getSetNums().size())) {
            throw new RuntimeException("Workout not formatted properly");
        }

        workout.setExercises(new ArrayList<>());
        WorkoutExerciseDao newExercise = null;
        List<SetDao> sets = new ArrayList<>();
        int exerciseNum = 0;
        for (int i = 0; i < workoutDto.getReps().size(); i++) {
            if (workoutDto.getSetNums().get(i) == 1) {
                if ( i != 0) {
                    workout.getExercises().add(newExercise);
                }
                newExercise = WorkoutExerciseDao.builder().exerciseNum(exerciseNum).exerciseId(workoutDto.getExercisesId().get(exerciseNum)).sets(new ArrayList<>()).build();
                sets = new ArrayList<>();
            }
            sets.add(SetDao.builder()
                    .exerciseId(workoutDto.getExercisesId().get(exerciseNum))
                    .setNum(workoutDto.getSetNums().get(i))
                    .warmup(workoutDto.getWarmups().get(i))
                    .reps(workoutDto.getReps().get(i))
                    .weight(workoutDto.getWeights().get(i))
                    .isCompleted(false)
                    .annotation(null)
                    .exerciseNum(exerciseNum)
                    .build());
        }

        return workout;

    }
}
