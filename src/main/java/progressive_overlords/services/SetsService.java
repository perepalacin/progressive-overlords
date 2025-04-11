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

    public SetDao getById(int id) {
        SetDao set = setsRepository.getById(id);
        set.setCompleted(true);
        return set;
    }

    private List<SetDao> getSetListBySetId (int setId) {
        return setsRepository.getListBySetId(setId);
    }

    public SetDao getByWorkoutIdExerciseNumAndSetNum(SetDao set) {
        SetDao foundSet = setsRepository.getByWorkoutIdExerciseNumIdAndSetNum(set.getWorkoutId(), set.getExerciseNum(), set.getExerciseId(), set.getSetNum());
        set.setCompleted(true);
        return foundSet;
    }

    public SetDao uploadWorkoutSet (SetDao newSet) {
        SetDao setAlreadyExists = this.getByWorkoutIdExerciseNumAndSetNum(newSet);
        if (setAlreadyExists != null) {
            //TODO: I need to add this because the ongoing workout view doesn't add sets
            newSet.setId(setAlreadyExists.getId());
            newSet = this.editSet(newSet);
            return newSet;
        }
        SetDao set = setsRepository.createSet(newSet);
        set.setCompleted(true);
        return set;
    }

    public SetDao editSet (SetDao newSet) {
        SetDao set = setsRepository.updateSet(newSet);
        set.setCompleted(true);
        return set;
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
        setsRepository.updateSetList(newSets);
    }

    public void deleteExerciseFromWorkout (int workoutId, int exerciseNum, int exerciseId) {
        setsRepository.deleteExerciseFromWorkout(workoutId, exerciseNum, exerciseId);
    }
}
