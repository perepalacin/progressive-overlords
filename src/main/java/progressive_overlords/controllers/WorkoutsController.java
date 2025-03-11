package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.WorkoutService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("${api.prefix}/workouts")
@RequiredArgsConstructor
public class WorkoutsController {

    private final WorkoutService workoutService;

    @GetMapping("/templates")
    public String getUserTemplates (Model model) {
        List<WorkoutDao> templates = workoutService.getUserTemplates();
        if (templates.isEmpty()) {
            return "responses/workout-templates/no-templates";
        }
        model.addAttribute("templates", templates);
        return "responses/workout-templates/templates-list";
    }

    @GetMapping("/templates/{templateId}")
    public String getUserTemplates (@PathVariable int templateId, Model model) {
        WorkoutDao template = workoutService.getUserTemplateById(templateId);
        if (template == null) {
            return "responses/workout-templates/no-templates";
        }
        model.addAttribute("template", template);
        return "responses/workout-templates/template-view";
    }

    @PostMapping("/templates")
    public String createTemplate (@Valid @RequestBody WorkoutDto templateDto, Model model) {
        WorkoutDao templateDao = workoutService.createTemplate(templateDto);
        model.addAttribute("template", templateDao);
        return "responses/workout-templates/template-created";
    }

    @PatchMapping("/templates")
    public String editTemplate (@Valid @RequestBody WorkoutDto templateDto, Model model) {
        WorkoutDao templateDao = workoutService.editTemplate(templateDto.getId(), templateDto);
        model.addAttribute("template", templateDao);
        return "responses/workout-templates/template-created";
    }

    @PostMapping("/start")
    public String startWorkout(@Valid @ModelAttribute WorkoutDto workoutDto) throws IOException {
        int workoutId = workoutService.startWorkout(workoutDto);
        return "redirect:/workout/" + workoutId;
    }

    @PatchMapping("/finish/{workoutId}")
    public String finishWorkout(@PathVariable int workoutId) throws IOException {
        workoutService.finishWorkout(workoutId);
        return "redirect:/workout-finished/" + workoutId;
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable int workoutId, Model model) {
        boolean result = workoutService.deleteWorkout(workoutId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("HX-Trigger", "ShowToast");
        if (result) {
            headers.add("X-Message", "success: Workout deleted successfully!"); // Custom header
        } else {
            headers.add("X-Message", "error: We couldn't delete your workout, please try again later"); // Custom header
        }
        return ResponseEntity.ok().headers(headers).build();

    }

}
