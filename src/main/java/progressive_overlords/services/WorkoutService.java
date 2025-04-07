package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.repositories.WorkoutRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final RoutinesService routinesService;

    public WorkoutDao getById(int workoutId) {
        WorkoutDao workoutDao = workoutRepository.getById(workoutId);
        if (workoutDao == null) {
            return null;
        }
        if (workoutDao.getEndDate() == null && !workoutDao.isTemplate() && workoutDao.getTemplateId() != null) {
            WorkoutDao routine = routinesService.getById(workoutDao.getTemplateId());
            if (routine != null) {
                workoutDao.setExercises(this.mergeSetsWithTemplate(workoutDao, routine));
            }
        }
        return workoutDao;
    }

    public int startWorkout (WorkoutDto workoutDto) {
        return workoutRepository.startWorkout(workoutDto);
    }

    private List<WorkoutExerciseDao> mergeSetsWithTemplate(WorkoutDao workout, WorkoutDao routine) {
        if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
            if (routine.getExercises() == null || routine.getExercises().isEmpty()) {
                return new ArrayList<>();
            }
            return routine.getExercises();
        }

        if (routine.getExercises() == null ||routine.getExercises().isEmpty()) {
            return workout.getExercises();
        }

        List<WorkoutExerciseDao> result = new ArrayList<>();

        for (WorkoutExerciseDao exercise : routine.getExercises()) {
            result.add(exercise);
            for(SetDao set : exercise.getSets()) {
                set.setCompleted(false);
            }
        }

        int resultExerciseNum = 0;
        for (WorkoutExerciseDao workoutExercise : workout.getExercises()) {
            for (int  i = resultExerciseNum; i < result.size(); i++ ) {
                if (workoutExercise.getExerciseNum() == result.get(i).getExerciseNum()) {
                    resultExerciseNum = i;
                    if (workoutExercise.getExerciseId() == result.get(i).getExerciseId()) {
                        List<SetDao> resultingSets = new ArrayList<>();
                        int workoutSetIndex = 0;
                        for (SetDao set : result.get(i).getSets()) {
                            if (workoutSetIndex >= workoutExercise.getSets().size()) {
                                break;
                            }
                            if (set.getSetNum() == workoutExercise.getSets().get(workoutSetIndex).getSetNum()) {
                                workoutExercise.getSets().get(workoutSetIndex).setCompleted(true);
                                resultingSets.add(workoutExercise.getSets().get(workoutSetIndex));
                                workoutSetIndex++;
                            }
                        }
                        result.get(i).setSets(resultingSets);
                    } else {
                        result.set(i, new WorkoutExerciseDao(workoutExercise.getExercise(), workoutExercise.getExerciseId(), workoutExercise.getExerciseNum(), workoutExercise.getSets()));
                    }
                }
            }
        }
        return result;
    }

}
