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
        SetDao set = setsRepository.getById(id);
        set.setCompleted(true);
        return set;
    }

    public SetDao uploadWorkoutSet (SetDao newSet) {
        SetDao set = setsRepository.createSet(newSet);
        set.setCompleted(true);
        return set;
    }

    public SetDao editSet (SetDao newSet) {
        SetDao set = setsRepository.updateSet(newSet);
        set.setCompleted(true);
        return set;
    }

    public void deleteSet(int setId) {
        setsRepository.deleteSet(setId);
    }
}
