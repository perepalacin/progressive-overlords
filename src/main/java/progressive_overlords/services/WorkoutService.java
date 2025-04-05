package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.ExerciseDao;
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
            return routine.getExercises();
        }

        if (routine.getExercises() == null ||routine.getExercises().isEmpty()) {
            return workout.getExercises();
        }

        int workoutExerciseIndex = 0;
        for (WorkoutExerciseDao exercise : routine.getExercises()) {

            exercise.getExerciseId();
        }


    }

}
