package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.repositories.WorkoutRepository;

import java.util.ArrayList;
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
            routine.getExercises().forEach(workoutExerciseDao -> workoutExerciseDao.getSets().forEach(set -> set.setWorkoutId(workout.getId())));
            return routine.getExercises();
        }

        if (routine.getExercises() == null ||routine.getExercises().isEmpty()) {
            return workout.getExercises();
        }

        List<WorkoutExerciseDao> result = new ArrayList<>();

        routine.getExercises().forEach(workoutExerciseDao -> workoutExerciseDao.getSets().forEach(set -> set.setWorkoutId(workout.getId())));

        for (WorkoutExerciseDao routineExercise : routine.getExercises()) {

            WorkoutExerciseDao newExercise = WorkoutExerciseDao.builder()
                    .exercise(routineExercise.getExercise())
                    .exerciseNum(routineExercise.getExerciseNum())
                    .exerciseId(routineExercise.getExerciseId())
                    .sets(routineExercise.getSets())
                    .build();

            for (WorkoutExerciseDao workoutExercise : workout.getExercises()) {
                if (workoutExercise.getExerciseNum() == newExercise.getExerciseNum()) {
                    if (workoutExercise.getExerciseId() == newExercise.getExerciseId()) {
                        for (int i = 0; i < newExercise.getSets().size(); i++) {
                            for (SetDao workoutSet : workoutExercise.getSets()) {
                                if (workoutSet.getSetNum() == newExercise.getSets().get(i).getSetNum()) {
                                    workoutSet.setCompleted(true);
                                    newExercise.getSets().set(i, workoutSet);
                                }
                            }
                        }
                    } else {
                        for(SetDao setDao : workoutExercise.getSets()) {
                            setDao.setCompleted(true);
                        }

                        newExercise = WorkoutExerciseDao.builder()
                                .exercise(workoutExercise.getExercise())
                                .exerciseNum(workoutExercise.getExerciseNum())
                                .exerciseId(workoutExercise.getExerciseId())
                                .sets(workoutExercise.getSets())
                                .build();
                        //TODO: fill blank sets with empty forms on the server side!!
                    }
                    //Add the exercise to the found exercises set;
                    break;
                }
            }
            //TODO: Add exercises that are not in the routine
            result.add(newExercise);
        }

        return result;
    }

}
