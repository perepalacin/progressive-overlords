package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.repositories.SetsRepository;

@Service
@RequiredArgsConstructor
public class SetsService {

    private final SetsRepository setsRepository;

    public SetDao getById(int id) {
        return setsRepository.getById(id);
    }

    public SetDao uploadWorkoutSet (SetDao newSet) {
        return setsRepository.createSet(newSet);
    }

    public SetDao editSet (SetDao newSet) {
        return setsRepository.updateSet(newSet);
    }

    public void deleteSet(int workoutId, int exerciseNum, int setId) {

    }
}
