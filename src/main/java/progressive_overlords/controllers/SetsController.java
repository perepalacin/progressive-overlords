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
@RequiredArgsConstructor
public class SetsController {

    private final SetsService setsService;

    @PostMapping("/api/v1/sets")
    public String uploadSet(@Valid @ModelAttribute SetDao newSet, Model model) {
        SetDao createdSet = setsService.uploadWorkoutSet(newSet);
        model.addAttribute("set", createdSet);
        return "components/sets/workout-set";
    }

    @PatchMapping("/api/v1/sets/{setId}")
    public String editSet(@PathVariable int setId, @Valid @ModelAttribute SetDao newSet, Model model) {
        newSet.setId(setId);
        SetDao createdSet = setsService.editSet(newSet);
        model.addAttribute("set", createdSet);
        return "components/sets/workout-set";
    }

    @DeleteMapping("/api/v1/sets/{setId}")
    public ResponseEntity<Void> deleteSet(@PathVariable int setId) {
        setsService.deleteSet(setId);
        return ResponseEntity.status(204)
                .header("HX-Trigger", "removeSet")
                .header("X-Message", String.valueOf(setId))
                .build();
    }

    @DeleteMapping("/api/v1/sets/exercise")
    public ResponseEntity<Void> deleteSet(@RequestParam int workoutId, @RequestParam int exerciseNum, @RequestParam int exerciseId) {
        setsService.deleteExerciseFromWorkout(workoutId, exerciseNum, exerciseId);
        return ResponseEntity.status(204)
                .header("HX-Trigger", "removeExercise")
                .header("X-Message", String.valueOf(exerciseNum))
                .build();
    }

}
