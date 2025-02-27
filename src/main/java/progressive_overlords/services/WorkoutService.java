package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.TemplateDto;
import progressive_overlords.repositories.TemplatesRepository;
import progressive_overlords.repositories.WorkoutsRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

//    private final TemplatesRepository templatesRepository;
//    private final WorkoutsRepository workoutsRepository;
//
//    public List<WorkoutDao> getUserTemplates(String userId) {
//        return workoutsRepository.getAllTemplatesByUserId(UUID.fromString(userId));
//    }
//
//    public WorkoutDao findTemplate(int templateId, String userId) {
//        return workoutsRepository.ge(templateId, UUID.fromString(userId));
//    }
//
//    public WorkoutDao createTemplate (TemplateDto templateDto, String userId) {
//        WorkoutDao templateDao = new WorkoutDao(templateDto.getName(), templateDto.getDescription(), templateDto.getColor(), templateDto.getBodyPart(), templateDto.getTags(), templateDto.getExercisesId(), templateDto.getSets(), templateDto.getReps());
//        return templatesRepository.saveTemplate(templateDao, UUID.fromString(userId));
//    }
//
//    public WorkoutDao editTemplate (int templateId, TemplateDto templateDto, String userId) {
//        WorkoutDao templateDao = new WorkoutDao(templateDto.getName(), templateDto.getDescription(), templateDto.getColor(), templateDto.getBodyPart(), templateDto.getTags(), templateDto.getExercisesId(), templateDto.getSets(), templateDto.getReps());
//        return templatesRepository.editTemplate(templateId, templateDao, UUID.fromString(userId));
//    }
//
//    public boolean deleteTemplate(int templateId, String userId) {
//        return templatesRepository.deleteTemplate(templateId, UUID.fromString(userId));
//    }
//
//    public WorkoutDao startWorkout (Integer templateId, UUID userId) {
//        WorkoutDao templateDao = null;
//        WorkoutDao lastSimiliarWorkout = null;
//        if (templateId != null) {
//            //Instead of doing this, get just the exercises! not the actual info, it can be an array and we parse it!
//            lastSimiliarWorkout =  workoutsRepository.getLastByTemplateId(templateId, userId);
//            if (lastSimiliarWorkout == null) {
//                templateDao = templatesRepository.getByTemplateId(templateId, userId);
//            }
//        }
//        //if we have the last workout, pass in the lest if exercises to build the ui!
//        int newWorkoutId = workoutsRepository.createAndStartWorkout(templateDao, userId);
//        WorkoutDao workoutDao = new WorkoutDao(templateDao, new Date().toString(), "");
//        workoutDao.setId(newWorkoutId);
//        return workoutDao;
//    }
//
//    public WorkoutDao endWorkout(int workoutId, UUID userId) {
//        workoutsRepository.endWorkout(workoutId, userId);
//        return this.getById(workoutId, userId);
//    }
//
//    public WorkoutDao getById (int workoutId, UUID userId) {
//        return workoutsRepository.getById(workoutId, userId);
//    }
//
//    public WorkoutDao findLastByTemplateId (int templateId, UUID userId) {
//        return workoutsRepository.getLastByTemplateId(templateId, userId);
//
//    }
}
