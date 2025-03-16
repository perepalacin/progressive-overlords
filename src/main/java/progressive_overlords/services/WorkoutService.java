package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.repositories.WorkoutsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutsRepository workoutsRepository;

    public List<WorkoutDao> getUserTemplates() {
        return workoutsRepository.getAllTemplatesFromUser();
    }

    public WorkoutDao getUserTemplateById(int templateId) {
        return workoutsRepository.getTemplateById(templateId);
    }

    public WorkoutDao getWorkoutById(int workoutId) {
        WorkoutDao workoutDao = workoutsRepository.getWorkoutById(workoutId);
        if (workoutDao == null) {
            return null;
        }
        if (workoutDao.getEndDate() == null && !workoutDao.isTemplate() && workoutDao.getTemplateId() != null) {
            WorkoutDao template = workoutsRepository.getTemplateById(workoutDao.getTemplateId());
            if (template != null) {
                workoutDao.mergeSetsWithTemplate(template);
            }
        }
        return workoutDao;
    }

    public int startWorkout(WorkoutDto workoutDto) {
        return workoutsRepository.startWorkout(workoutDto);
    }

    public void finishWorkout(int workoutId) {
        workoutsRepository.finishWorkout(workoutId);
    }

    public WorkoutDao createTemplate(WorkoutDto templateDto) {
        WorkoutDao templateDao = WorkoutDao.builder().name(templateDto.getName()).description(templateDto.getDescription()).color(templateDto.getColor()).isTemplate(true).templateId(null).unparsedTags(templateDto.getUnparsedTags()).build();
        templateDao.setExercisesDaoFromSetsDto(templateDto.getSets());
        return workoutsRepository.saveTemplate(templateDao);
    }

    public WorkoutDao editTemplate(int templateId, WorkoutDto templateDto) {
        WorkoutDao templateDao = WorkoutDao.builder().id(templateId).name(templateDto.getName()).description(templateDto.getDescription()).color(templateDto.getColor()).isTemplate(true).templateId(null).unparsedTags(templateDto.getUnparsedTags()).build();
        templateDao.setExercisesDaoFromSetsDto(templateDto.getSets());
        return workoutsRepository.editTemplate(templateDao);
    }

    public boolean deleteWorkout(int workoutId) {
        return workoutsRepository.deleteWorkout(workoutId);
    }
}
