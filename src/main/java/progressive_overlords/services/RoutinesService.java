package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.mappers.WorkoutMapper;
import progressive_overlords.repositories.RoutinesRepository;

@Service
@RequiredArgsConstructor
public class RoutinesService {

    private final RoutinesRepository routinesRepository;

    public WorkoutDao saveRoutine (WorkoutDto routineRequest) {
        WorkoutDao newRoutine;
        try {
            newRoutine = WorkoutMapper.mapDtoToDao(routineRequest);
        } catch (Exception e) {
            throw new BadRequestException("Routine request is not properly formatted");
        }
        return routinesRepository.saveRoutine(newRoutine);
    }
}
