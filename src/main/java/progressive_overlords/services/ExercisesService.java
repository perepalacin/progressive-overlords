package progressive_overlords.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.entities.dao.ExerciseUserDataDao;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.repositories.ExercisesRepository;
import progressive_overlords.repositories.SetsRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExercisesService {

    private final ExercisesRepository exercisesRepository;
    private List<ExerciseDao> inMemoryExerciseList = new ArrayList<>();
    private final int pageSize = 20;
    private int maxPages = 0;
    private final SetsRepository setsRepository;

    @PostConstruct
    private void loadExerciseList() {
        inMemoryExerciseList = exercisesRepository.getAll();
        maxPages = inMemoryExerciseList.size() / pageSize;
    }

    public List<ExerciseDao> getAll() {
        return exercisesRepository.getAll();
    }

    public ExerciseDao getById(int id) {
        for (ExerciseDao exerciseDao : inMemoryExerciseList) {
            if (exerciseDao.getId() == id) {
                return exerciseDao;
            }
        }
        return null;
    }

    public List<ExerciseDao> getListByIds(List<Integer> ids) {
        List<ExerciseDao> result = new ArrayList<>();
        for (int i = 0; i < inMemoryExerciseList.size(); i++) {
            if (ids.contains(inMemoryExerciseList.get(i).getId())) {
                result.add(inMemoryExerciseList.get(i));
            }
        }
        return result;
    }

    public List<ExerciseDao> getExercises(int page, String query) {
        if (page >= maxPages) {
            return null;
        }

        List<ExerciseDao> result = new ArrayList<>();
        if (query != null && !query.isEmpty()) {
            int matches = 0;
            for (int i = 0; i < inMemoryExerciseList.size(); i++) {
                if (inMemoryExerciseList.get(i).getName().toLowerCase().contains((query))) {
                    if (matches >= page*20 && matches < (page+1)*20) {
                        result.add(inMemoryExerciseList.get(i));
                    }
                    matches++;
                    if (matches > (page+1)*20) {
                        return result;
                    }
                }
            }
        } else {
            for (int i = page*20; i < (page+1)*20; i++) {
                result.add(inMemoryExerciseList.get(i));
            }
        }
        return result;
    }

    public ArrayList<WorkoutExerciseDao> generateExerciseListFromSets(List<SetDao> sets, int workoutId)  {
        if (sets == null || sets.isEmpty()) {
            throw new BadRequestException("No sets were provided.");
        }

        HashMap<Integer, WorkoutExerciseDao> exerciseMap = new HashMap<>();
        HashSet<Integer> foundExercisesAndSets = new HashSet<>();

        try {
            for (SetDao set : sets) {
                if (exerciseMap.containsKey(set.getExerciseNum())) {
                    SetDao newSet = SetDao.builder()
                            .id(set.getId())
                            .exerciseNum(set.getExerciseNum())
                            .setNum(set.getSetNum())
                            .exerciseId(set.getExerciseId())
                            .reps(set.getReps())
                            .weight(set.getWeight())
                            .warmup(set.isWarmup())
                            .workoutId(workoutId)
                            .build();
                    exerciseMap.get(set.getExerciseNum()).getSets().add(newSet);
                } else {
                    ExerciseDao exerciseDao = this.getById(set.getExerciseId());
                    WorkoutExerciseDao newExercise = WorkoutExerciseDao.builder()
                            .exerciseNum(set.getExerciseNum())
                            .exerciseId(set.getExerciseId())
                            .sets(new ArrayList<>())
                            .exercise(exerciseDao)
                            .build();
                    SetDao newSet = SetDao.builder().
                            id(set.getId())
                            .exerciseNum(set.getExerciseNum())
                            .setNum(set.getSetNum())
                            .exerciseId(set.getExerciseId())
                            .reps(set.getReps())
                            .weight(set.getWeight())
                            .workoutId(workoutId)
                            .warmup(set.isWarmup())
                            .build();
                    newExercise.getSets().add(newSet);
                    exerciseMap.put(set.getExerciseNum(), newExercise);
                }
            }

            ArrayList<WorkoutExerciseDao> result  = new ArrayList<>(exerciseMap.values());
            result.sort(Comparator.comparingInt(WorkoutExerciseDao::getExerciseNum));
            for(WorkoutExerciseDao exercise : result) {
                exercise.getSets().sort(Comparator.comparingInt(SetDao::getSetNum));
            }
            return result;
        } catch (Exception e) {
            throw new BadRequestException("There is a mismatch between the number of exercises and sets selected. Please review your template.");
        }
    }

    public ExerciseUserDataDao getExerciseUserData (int exerciseId) {
        List<SetDao> userSets = setsRepository.getLastUserExerciseSets(exerciseId, 200);
        if (userSets == null) {
            return null;
        }
        long repsCount = 0L;
        ExerciseUserDataDao result = ExerciseUserDataDao.builder()
                .erp(0)
                .avgReps(0)
                .maxWeight(0)
                .values(null)
                .build();
        HashMap<Integer, ExerciseUserDataDao.ErpValue> erpValuesMap = new HashMap<>();
        for(SetDao set : userSets) {
            if (set.getWeight() > result.getMaxWeight()) {
                result.setMaxWeight(set.getWeight());
            }
            repsCount += (long) set.getReps();
            double erp = calculateErp(set.getReps(), set.getWeight());
            if (erp > result.getErp()) {
                result.setErp(erp);
            }
            erpValuesMap.compute(set.getWorkoutId(), (key, existingValue) -> {
                if (existingValue == null || erp > existingValue.getErp()) {
                    return ExerciseUserDataDao.ErpValue.builder()
                            .erp(erp)
                            .date(set.getCreatedAt())
                            .build();
                }
                return existingValue;
            });
            result.setAvgReps(repsCount/ (double) userSets.size());
        }
        result.setValues(erpValuesMap.values().stream().toList());
        return result;
    }

    private double calculateErp(double reps, double weight) {
        return (weight) * (1+ (reps/30));
    }
}
