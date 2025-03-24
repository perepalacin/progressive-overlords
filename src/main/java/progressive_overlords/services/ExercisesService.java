package progressive_overlords.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.repositories.ExercisesRepository;

import java.util.ArrayList;
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
        for (int i = 0; i < inMemoryExerciseList.size(); i++) {
            if (inMemoryExerciseList.get(i).getId() == id) {
                return inMemoryExerciseList.get(i);
            }
        }
        return null;
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
}
