package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.exceptions.BadRequestException;
import progressive_overlords.services.SetsService;

@Controller
@RequestMapping("${api.prefix}/sets")
@RequiredArgsConstructor
public class SetsController {

    private final SetsService setsService;

    @PostMapping
    public String uploadSet(@Valid @ModelAttribute SetDao set, Model model) {
        SetDao setDao = setsService.createSet(set);
        model.addAttribute("set", setDao);
        return "responses/sets/new-set";
    }

    @PatchMapping
    public String editSet(@Valid @ModelAttribute SetDao set, Model model) {
        SetDao setDao = setsService.editSet(set);
        model.addAttribute("set", setDao);
        return "responses/sets/new-set";
    }

    @DeleteMapping("/exercises")
    public ResponseEntity<Void> deleteExercise(@RequestParam int exerciseId, @RequestParam int workoutId) {
        if (exerciseId == 0 || workoutId == 0) {
            throw new BadRequestException("Improper workout or exercise marked for deletion");
        }
        setsService.deleteExerciseFromWorkout(workoutId, exerciseId);
        return ResponseEntity.status(204)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "success: Exercise deleted successfully!")
                .build();
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<Void> deleteSet(@PathVariable int setId) {
        setsService.deleteSet(setId);
        return ResponseEntity.status(204)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "success: Set deleted successfully!")
                .build();
    }

}
