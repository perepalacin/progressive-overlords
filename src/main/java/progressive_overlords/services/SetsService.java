package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.repositories.SetsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SetsService {

    private final SetsRepository setsRepository;

    public SetDao createSet(SetDao setDao) {
        return setsRepository.createSet(setDao);
    }

    public SetDao editSet(SetDao setDao) {
        return setsRepository.editSet(setDao);
    }

    @Transactional
    public void deleteSet(int setId) {
        List<SetDao> sameExerciseSets = this.getSetListBySetId(setId);
        boolean foundSet = false;
        List<SetDao> newSets = new ArrayList<>();
        for(SetDao set : sameExerciseSets) {
            if (set.getId() == setId) {
                foundSet = true;
            } else {
                if (foundSet) {
                    set.setSetNum(set.getSetNum()-1);
                }
                newSets.add(set);
            }
        }
        setsRepository.deleteSet(setId);
        setsRepository.editSetList(newSets);
    }

    public void deleteExerciseFromWorkout (int workoutId, int exerciseId) {
        setsRepository.deleteExerciseFromWorkout(workoutId, exerciseId);
    }

    private List<SetDao> getSetListBySetId (int setId) {
        return setsRepository.getListBySetId(setId);
    }

}
