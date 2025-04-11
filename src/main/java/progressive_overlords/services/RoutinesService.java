package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.mappers.WorkoutMapper;
import progressive_overlords.repositories.RoutinesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutinesService {

    private final RoutinesRepository routinesRepository;

    public WorkoutDao getById (int routineId) {
        return routinesRepository.getById(routineId);
    }

    public List<WorkoutDao> getAllFromUser () {
        return routinesRepository.getAllFromUser();
    }


    public boolean findIfExists (int routineId) {
        return routinesRepository.findIfExists(routineId);
    }

    public WorkoutDao saveRoutine (WorkoutDto routineRequest) {
        WorkoutDao newRoutine;
        try {
            // TODO: Fix routine with duplicated exercises!
            newRoutine = WorkoutMapper.mapDtoToDao(routineRequest);
        } catch (Exception e) {
            throw new BadRequestException("Routine request is not properly formatted");
        }
        return routinesRepository.saveRoutine(newRoutine);
    }

    public WorkoutDao editRoutine (WorkoutDto routineRequest) {
        if (!this.findIfExists(routineRequest.getId())) {
            throw new BadRequestException("Routine with this id doesn't exist");
        }
        WorkoutDao newRoutine;
        try {
            newRoutine = WorkoutMapper.mapDtoToDao(routineRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName());
            throw new BadRequestException("Routine request is not properly formatted");
        }
        return routinesRepository.updateRoutine(newRoutine);
    }

    public boolean delete (int routineId) {
        if (!this.findIfExists(routineId)) {
            throw new BadRequestException("Routine with this id doesn't exist");
        }
        return routinesRepository.delete(routineId);
    }
}
