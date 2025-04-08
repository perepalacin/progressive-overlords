package progressive_overlords.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutExerciseDao;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.repositories.ExercisesRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercisesService {

    private final ExercisesRepository exercisesRepository;
    private List<ExerciseDao> inMemoryExerciseList = new ArrayList<>();
    private final int pageSize = 20;
    private int maxPages = 0;

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
        System.out.println(maxPages);
        if (page >= maxPages) {
            return null;
        }

        List<ExerciseDao> result = new ArrayList<>();
        if (query != null && !query.isEmpty()) {
            int matches = 0;
            for (int i = 0; i < inMemoryExerciseList.size(); i++) {
                System.out.println(inMemoryExerciseList.get(i).getName().toLowerCase() + " - " + query + " = " + inMemoryExerciseList.get(i).getName().toLowerCase().contains((query)));
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

        try {
            for (SetDao set : sets) {
                if (exerciseMap.containsKey(set.getExerciseId())) {
                    SetDao newSet = SetDao.builder()
                            .id(set.getId())
                            .exerciseNum(exerciseMap.get(set.getExerciseId()).getSets().get(0).getExerciseNum())
                            .setNum(set.getSetNum())
                            .exerciseId(set.getExerciseId())
                            .reps(set.getReps())
                            .weight(set.getWeight())
                            .warmup(set.isWarmup())
                            .workoutId(workoutId)
                            .build();
                    exerciseMap.get(set.getExerciseId()).getSets().add(newSet);
                } else {
                    ExerciseDao exerciseDao = this.getById(set.getExerciseId());
                    WorkoutExerciseDao newExercise = WorkoutExerciseDao.builder()
                            .exerciseNum(exerciseMap.size())
                            .exerciseId(set.getExerciseId())
                            .sets(new ArrayList<>())
                            .exercise(exerciseDao)
                            .build();
                    SetDao newSet = SetDao.builder().
                            id(set.getId())
                            .exerciseNum(exerciseMap.size())
                            .setNum(set.getSetNum())
                            .exerciseId(set.getExerciseId())
                            .reps(set.getReps())
                            .weight(set.getWeight())
                            .workoutId(workoutId)
                            .warmup(set.isWarmup())
                            .build();
                    newExercise.getSets().add(newSet);
                    exerciseMap.put(set.getExerciseId(), newExercise);
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
}
