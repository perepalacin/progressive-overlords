package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dto.TemplateDto;
import progressive_overlords.repositories.TemplatesRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplatesService {

    private final TemplatesRepository templatesRepository;

    public List<TemplateDao> getUserTemplates(String userId) {
        return templatesRepository.getAllByUserId(UUID.fromString(userId));
    }

    public TemplateDao findTemplate(int templateId, String userId) {
        return templatesRepository.getByTemplateId(templateId, UUID.fromString(userId));
    }

    public TemplateDao createTemplate (TemplateDto templateDto, String userId) {
        TemplateDao templateDao = new TemplateDao(templateDto.getName(), templateDto.getDescription(), templateDto.getColor(), templateDto.getBodyPart(), templateDto.getTags(), templateDto.getExercisesId(), templateDto.getSets(), templateDto.getReps());
        return templatesRepository.saveTemplate(templateDao, UUID.fromString(userId));
    }

    public TemplateDao editTemplate (int templateId, TemplateDto templateDto, String userId) {
        TemplateDao templateDao = new TemplateDao(templateDto.getName(), templateDto.getDescription(), templateDto.getColor(), templateDto.getBodyPart(), templateDto.getTags(), templateDto.getExercisesId(), templateDto.getSets(), templateDto.getReps());
        return templatesRepository.editTemplate(templateId, templateDao, UUID.fromString(userId));
    }

    public boolean deleteTemplate(int templateId, String userId) {
        return templatesRepository.deleteTemplate(templateId, UUID.fromString(userId));
    }
}
