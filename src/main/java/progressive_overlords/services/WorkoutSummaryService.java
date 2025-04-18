package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dao.WorkoutSummaryDao;
import progressive_overlords.repositories.WorkoutSummaryRepository;
import progressive_overlords.utils.TimeDiffHelper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutSummaryService {

    private final WorkoutSummaryRepository workoutSummaryRepository;
    private final ExercisesService exercisesService;

    public List<WorkoutSummaryDao> getFriendsActivitySummary (int page) {
        List<WorkoutSummaryDao> friendSummaries = workoutSummaryRepository.getFriendsActivity(page);
        for (WorkoutSummaryDao summary : friendSummaries) {
            for (WorkoutExerciseDao workoutExerciseDao : summary.getWorkoutExercises()) {
                workoutExerciseDao.setExercise(exercisesService.getById(workoutExerciseDao.getExerciseId()));
            }
        }
        return friendSummaries;
    }

    public List<WorkoutSummaryDao> getOwnActivity (int page) {
        List<WorkoutSummaryDao> ownSummaries = workoutSummaryRepository.getOwnActivity(page);
        for (WorkoutSummaryDao summary : ownSummaries) {
            for (WorkoutExerciseDao workoutExerciseDao : summary.getWorkoutExercises()) {
                workoutExerciseDao.setExercise(exercisesService.getById(workoutExerciseDao.getExerciseId()));
            }
        }
        return ownSummaries;
    }

    @Async
    public void createWorkoutSummary (WorkoutDao finishedWorkout, UUID userId) {
        finishedWorkout.generateWorkoutAggregatedData();
        WorkoutSummaryDao workoutSummaryDao = WorkoutSummaryDao.builder()
                .volume(finishedWorkout.getAggregatedWorkoutData().getVolume())
                .workoutExercises(finishedWorkout.getExercises())
                .duration(TimeDiffHelper.getTimeDifferenceInMillis(finishedWorkout.getStartDate(), finishedWorkout.getEndDate()))
                .startDate(finishedWorkout.getStartDate())
                .workoutId(finishedWorkout.getId())
                .publicUserDao(PublicUserDao.builder().userId(userId).build())
                .build();

        workoutSummaryRepository.save(workoutSummaryDao);
    }

}
