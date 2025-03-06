package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.repositories.SetsRepository;

@Service
@RequiredArgsConstructor
public class SetsService {

    private final SetsRepository setsRepository;

    public SetDao createSet(SetDao setDao) {
        return setsRepository.createSet(setDao);
    }

}
