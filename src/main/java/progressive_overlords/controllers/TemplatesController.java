package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.TemplateDao;
import progressive_overlords.entities.dto.TemplateDto;
import progressive_overlords.services.TemplatesService;

import java.util.UUID;

@Controller
@RequestMapping("${api.prefix}/templates")
@RequiredArgsConstructor
public class TemplatesController {

    @Value("${master.userId}")
    private String masterUserId;
    private final TemplatesService templatesService;

    @GetMapping("/")
    public String getUserTemplates () {
        return "";
    }

    @PostMapping
    public String createTemplate (@Valid @ModelAttribute TemplateDto templateDto, Model model) {
        TemplateDao templateDao = templatesService.createTemplate(templateDto, masterUserId);
        return "responses/workout-templates/template-created";
    }

}
