package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.RoutinesService;

@Controller
@RequiredArgsConstructor
public class RoutinesController {

    private final RoutinesService routinesService;

    @GetMapping("/create-routine")
    public String getCreateRoutineView (Model model) {
        return "pages/routines/create-edit-routine";
    }

    @PostMapping("/api/v1/routines")
    public ResponseEntity<Void> saveRoutine (@ModelAttribute WorkoutDto workoutDto) {
        WorkoutDao routine = routinesService.saveRoutine(workoutDto);
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/routine/" + routine.getId())
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "success: Routine created successfully!")
                .build();
    }


}
