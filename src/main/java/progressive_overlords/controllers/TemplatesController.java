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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("${api.prefix}/templates")
@RequiredArgsConstructor
public class TemplatesController {

    @Value("${master.userId}")
    private String masterUserId;
    private final TemplatesService templatesService;

    @GetMapping("")
    public String getUserTemplates (Model model) {
        List<TemplateDao> templates = templatesService.getUserTemplates(masterUserId);
        if (templates.isEmpty()) {
            return "responses/workout-templates/no-templates";
        }
        model.addAttribute("templates", templates);
        return "responses/workout-templates/templates-list";
    }

    @GetMapping("/{templateId}")
    public String getById(@PathVariable int templateId, Model model) {
        TemplateDao template = templatesService.findTemplate(templateId, masterUserId);
        if (template == null) {
            model.addAttribute("message", "The workout template you are looking for doesn't exist");
            return "responses/errors/404-not-found";
        }
        model.addAttribute("template", template);
        return "responses/workout-templates/template-view";
    }

    @PostMapping
    public String createTemplate (@Valid @ModelAttribute TemplateDto templateDto, Model model) {
        TemplateDao templateDao = templatesService.createTemplate(templateDto, masterUserId);
        model.addAttribute("template", templateDao);
        return "responses/workout-templates/template-created";
    }

    @PatchMapping("/{templateId}")
    public String editTemplate (@PathVariable int templateId, @Valid @ModelAttribute TemplateDto templateDto, Model model) {
        TemplateDao templateDao = templatesService.editTemplate(templateId, templateDto, masterUserId);
        model.addAttribute("template", templateDao);
        return "responses/workout-templates/template-created";
    }

    @DeleteMapping("/{templateId}")
    public String deleteTemplate(@PathVariable int templateId, Model model) {
        boolean isDeleted = templatesService.deleteTemplate(templateId, masterUserId);
        model.addAttribute("message", isDeleted ? "Template deleted successfully" : "Sorry we couldn't delete your template");
        return "responses/workout-templates/template-deleted";
    }
}
