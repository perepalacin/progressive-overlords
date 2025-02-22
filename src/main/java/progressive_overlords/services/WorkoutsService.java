package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.repositories.TemplatesRepository;
import progressive_overlords.repositories.WorkoutsRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutsService {

    private final WorkoutsRepository workoutsRepository;
    private final TemplatesRepository templatesRepository;

    public WorkoutDao getById (int workoutId, UUID userId) {
        return workoutsRepository.getWorkoutById(workoutId, userId);
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
//    public WorkoutDao findLastByTemplateId (int templateId, UUID userId) {
//        return workoutsRepository.find
//    }


}
