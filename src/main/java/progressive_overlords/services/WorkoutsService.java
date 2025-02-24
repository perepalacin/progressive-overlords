package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.repositories.TemplatesRepository;
import progressive_overlords.repositories.WorkoutsRepository;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutsService {

    private final WorkoutsRepository workoutsRepository;
    private final TemplatesRepository templatesRepository;

    public WorkoutDao startWorkout (Integer templateId, UUID userId) {
        TemplateDao templateDao = null;
        WorkoutDao lastSimiliarWorkout = null;
        if (templateId != null) {
            //Instead of doing this, get just the exercises! not the actual info, it can be an array and we parse it!
            lastSimiliarWorkout =  workoutsRepository.getLastByTemplateId(templateId, userId);
            if (lastSimiliarWorkout == null) {
                templateDao = templatesRepository.getByTemplateId(templateId, userId);
            }
        }
        //if we have the last workout, pass in the lest if exercises to build the ui!
        int newWorkoutId = workoutsRepository.createAndStartWorkout(templateDao, userId);
        WorkoutDao workoutDao = new WorkoutDao(templateDao, new Date().toString(), "");
        workoutDao.setId(newWorkoutId);
        return workoutDao;
    }

    public WorkoutDao endWorkout(int workoutId, UUID userId) {
        workoutsRepository.endWorkout(workoutId, userId);
        return this.getById(workoutId, userId);
    }

    public WorkoutDao getById (int workoutId, UUID userId) {
        return workoutsRepository.getById(workoutId, userId);
    }

    public WorkoutDao findLastByTemplateId (int templateId, UUID userId) {
        return workoutsRepository.getLastByTemplateId(templateId, userId);

    }


//    public WorkoutDao startWorkout (Integer templateId, String userId) {
//        if (templateId == null) {
//            return null; // If the workout template is null, we will provide a screen to configure the workout on the fly
//        }
//        // select the last workout with the same template id
//        // if it doesn't exist, return the template!
//    }
//
//    public WorkoutDao findByTemplateId (int templateId) {
//
//    }
//
//

}
