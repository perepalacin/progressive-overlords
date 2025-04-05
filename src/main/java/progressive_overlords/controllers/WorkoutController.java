package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.WorkoutService;

@Controller
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workout/{workoutId}")
    public String workoutView(@PathVariable int workoutId, Model model) {
        try {

        WorkoutDao workout = workoutService.getById(workoutId);
        if (workout != null) {
            model.addAttribute("workout", workout);
            return "pages/workouts/ongoing-workout-view";
        }
        return "404";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @PostMapping("/api/v1/workouts/start")
    public ResponseEntity<Void> startWorkout(@Valid @ModelAttribute WorkoutDto workoutDto) {
        int workoutId = workoutService.startWorkout(workoutDto);
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/workout/" + workoutId)
                .build();
    }

}
