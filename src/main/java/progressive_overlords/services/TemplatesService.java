package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dto.TemplateDto;
import progressive_overlords.repositories.TemplatesRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplatesService {

    private final TemplatesRepository templatesRepository;

    public TemplateDao createTemplate (TemplateDto templateDto, String userId) {
        return templatesRepository.saveTemplate(templateDto, UUID.fromString(userId));
    }

}
