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

    public List<ExerciseDao> getExercises(int page, String query) {
        if (page * pageSize >= maxPages) {
            throw new BadRequestException("Page exceeds the limit. The maximum page is " + maxPages);
        }

        List<ExerciseDao> result = new ArrayList<>();
//        if (query != null) {
//            int matches = 0;
//            for (int i = 0; i < inMemoryExerciseList.size(); i++) {
//                if (inMemoryExerciseList.get(i).getName().toLowerCase().equals((query))) {
//                    matches++;
//                    if ()
//                }
//                result.add(inMemoryExerciseList.get(i));
//            }
//        }
        for (int i = page*20; i < (page+1)*20; i++) {
            result.add(inMemoryExerciseList.get(i));
        }
        return exercisesRepository.getWithoutQuery(page);
    }
}
