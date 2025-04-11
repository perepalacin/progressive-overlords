package progressive_overlords.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import progressive_overlords.entities.dao.AggregatedWorkoutDataDao;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.repositories.WorkoutRepository;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final RoutinesService routinesService;

    public WorkoutDao getById(int workoutId) {
        WorkoutDao workoutDao = workoutRepository.getById(workoutId);
        if (workoutDao == null) {
            //Throw exception here! so that we don't have to throw the 404 page everywhere!
            return null;
        }
        if (workoutDao.getEndDate() == null && !workoutDao.isTemplate() && workoutDao.getTemplateId() != null) {
            WorkoutDao routine = routinesService.getById(workoutDao.getTemplateId());
            if (routine != null) {
                workoutDao.setExercises(this.mergeSetsWithTemplate(workoutDao, routine));
            }
        } else {
            workoutDao.generateWorkoutAggregatedData();
        }
        return workoutDao;
    }

    public boolean findIfExists (int workoutId) {
        return workoutRepository.findIfExists(workoutId);
    }


    public int startWorkout(WorkoutDto workoutDto) {
        return workoutRepository.startWorkout(workoutDto);
    }

    public void finishWorkout(int workoutId) {
        workoutRepository.updateWorkoutEndDate(workoutId);
    }

    private List<WorkoutExerciseDao> mergeSetsWithTemplate(WorkoutDao workout, WorkoutDao routine) {
        //Ensure both objects are sorted both exerciseNum and setNum wise, otherwise the output of this function will not be correct.
        //First check if workout has no exercises, if so return the template
        if (workout.getExercises() == null || workout.getExercises().isEmpty()) {
            if (routine.getExercises() == null || routine.getExercises().isEmpty()) {
                return new ArrayList<>();
            }
            routine.getExercises().forEach(workoutExerciseDao -> workoutExerciseDao.getSets().forEach(set -> set.setWorkoutId(workout.getId())));
            return routine.getExercises();
        }

        // if the template has no exercises, return the workout but filling the empty sets
        if (routine.getExercises() == null || routine.getExercises().isEmpty()) {
            return workout.getExercises();
        }

        List<WorkoutExerciseDao> result = new ArrayList<>();

        HashSet<Integer> foundExercises = new HashSet<>();
        for (WorkoutExerciseDao routineExercise : routine.getExercises()) {
            boolean foundExercise = false;
            for (WorkoutExerciseDao workoutExercise : workout.getExercises()) {
                if (workoutExercise.getExerciseNum() == routineExercise.getExerciseNum()) {
                    foundExercise = true;
                    foundExercises.add(workoutExercise.getExerciseNum());
                    if (workoutExercise.getExerciseId() == routineExercise.getExerciseId()) {
                        WorkoutExerciseDao newExercise = WorkoutExerciseDao.builder()
                                .exercise(workoutExercise.getExercise())
                                .exerciseId(workoutExercise.getExerciseId())
                                .exerciseNum(workoutExercise.getExerciseNum())
                                .sets(new ArrayList<>())
                                .build();
                        HashSet<Integer> foundSets = new HashSet<>();
                        for (SetDao routineSet : routineExercise.getSets()) {
                            boolean foundSet = false;
                            for (SetDao workoutSet : workoutExercise.getSets()) {
                                System.out.println("routine - w : " + routineSet.getSetNum() + " " + workoutSet.getSetNum());
                                if (workoutSet.getSetNum() == routineSet.getSetNum()) {
                                    workoutSet.setCompleted(true);
                                    foundSets.add(workoutSet.getId());
                                    System.out.println("Added new set from workout data " + workoutSet.getExerciseNum() + " - " + workoutSet.getSetNum() + " " + workoutSet.isCompleted());
                                    newExercise.getSets().add(workoutSet);
                                    foundSet = true;
                                    break;
                                }
                            }
                            if (!foundSet) {
                                routineSet.setWorkoutId(workout.getId());
                                System.out.println("Added new set from routine data " + routineSet.getExerciseNum() + " - " + routineSet.getSetNum() + " " + routineSet.isCompleted());
                                newExercise.getSets().add(routineSet);
                            }
                        }
                        for (SetDao workoutSet : workoutExercise.getSets()) {
                            if (!foundSets.contains(workoutSet.getId())) {
                                System.out.println("adding non found set");
                                workoutSet.setCompleted(true);
                                newExercise.getSets().add(workoutSet);
                            }
                        }
                        System.out.println("calling the fill method on workout exercises");
                        newExercise.setSets(this.fillExerciseSets(newExercise.getSets()));
                        result.add(newExercise);
                    } else {
                        workoutExercise.getSets().forEach(set -> {
                            set.setWorkoutId(workout.getId());
                            set.setCompleted(true);
                        });
                        workoutExercise.setSets(this.fillExerciseSets(workoutExercise.getSets()));
                        result.add(workoutExercise);
                    }
                    break;
                }
            }
            if (!foundExercise) {
                routineExercise.getSets().forEach(set -> set.setWorkoutId(workout.getId()));
                routineExercise.setSets(this.fillExerciseSets(routineExercise.getSets()));
                System.out.println("couldn't find exercise, adding the routine");
                result.add(routineExercise);
            }
        }

        for (WorkoutExerciseDao workoutExercise : workout.getExercises()) {
            System.out.println("didn't find the routine, adding the workout");
            if (!foundExercises.contains(workoutExercise.getExerciseNum())) {
                workoutExercise.getSets().forEach(set -> set.setCompleted(true));
                workoutExercise.setSets(this.fillExerciseSets(workoutExercise.getSets()));
                result.add(workoutExercise);
            }
        }
        return result;
    }

    private List<SetDao> fillExerciseSets(List<SetDao> exerciseSets) {
        List<SetDao> result = new ArrayList<>();
        int setNum = 1;
        for (SetDao exerciseSet : exerciseSets) {
            if (exerciseSet.getSetNum() == setNum) {
                result.add(exerciseSet);
                System.out.println("Added set from argument " + setNum + " " + exerciseSet.isCompleted() + " " + exerciseSet.getSetNum());
                setNum++;
            } else {
                for (; setNum < exerciseSet.getSetNum(); setNum++) {
                    SetDao placeholderSet;
                    if (result.isEmpty()) {
                        placeholderSet = SetDao.builder()
                                .exerciseId(exerciseSet.getExerciseId())
                                .exerciseNum(exerciseSet.getExerciseNum())
                                .isCompleted(false)
                                .reps(exerciseSet.getReps())
                                .weight(exerciseSet.getWeight())
                                .annotation(null)
                                .setNum(setNum)
                                .warmup(exerciseSet.isWarmup())
                                .workoutId(exerciseSet.getWorkoutId())
                                .build();
                    } else {
                        placeholderSet = SetDao.builder()
                                .exerciseId(result.get(result.size() - 1).getExerciseId())
                                .exerciseNum(result.get(result.size() - 1).getExerciseNum())
                                .isCompleted(result.get(result.size() - 1).isCompleted())
                                .reps(result.get(result.size() - 1).getReps())
                                .weight(result.get(result.size() - 1).getWeight())
                                .annotation(null)
                                .setNum(setNum)
                                .warmup(result.get(result.size() - 1).isWarmup())
                                .workoutId(result.get(result.size() - 1).getWorkoutId())
                                .build();
                    }
                    System.out.println("Added set as placeholder " + setNum + " " + String.valueOf(placeholderSet.isCompleted()) + " " + placeholderSet.getSetNum());
                    result.add(placeholderSet);
                }
                result.add(exerciseSet);
            }
        }
        return result;
    }

    public boolean delete (int workoutId) {
        if (!this.findIfExists(workoutId)) {
            throw new BadRequestException("Workout with this id doesn't exist");
        }
        return workoutRepository.delete(workoutId);
    }
}