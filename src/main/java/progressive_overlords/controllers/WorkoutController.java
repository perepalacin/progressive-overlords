package progressive_overlords.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dao.WorkoutSummaryDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.WorkoutService;
import progressive_overlords.services.WorkoutSummaryService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutSummaryService workoutSummaryService;

    @GetMapping("/workout/{workoutId}")
    public String getWorkoutView(@PathVariable int workoutId, Model model) {
        WorkoutDao workout = workoutService.getById(workoutId);
        if (workout != null) {
            model.addAttribute("workout", workout);
            UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("isFinished", workout.getEndDate() != null);
            if (!workout.getUserId().equals(userId)) {
                model.addAttribute("activeTab", "history");
                return "pages/errors/404"; //is a 403 in reality;
            }
            model.addAttribute("isEditable", workout.getUserId().equals(userId));
            return "pages/workouts/ongoing-workout-view";
        }
        model.addAttribute("activeTab", "history");
        return "pages/errors/404";
    }

    @GetMapping("/history")
    public String getOwnHistory(Model model) {
        List<WorkoutSummaryDao> workoutHistory =  workoutSummaryService.getOwnActivity(0);
        model.addAttribute("workoutHistory", workoutHistory);
        return "pages/workouts/workout-history";
    }

    @GetMapping("/api/v1/history")
    public String getPersonalWorkoutHistory(@RequestParam int page, Model model) {
        List<WorkoutSummaryDao> workoutHistory =  workoutSummaryService.getOwnActivity(page);
        model.addAttribute("feedActivity", workoutHistory);
        model.addAttribute("isOwnWorkout", true);
        model.addAttribute("newPage", page+1);
        return "components/feeds/feed-activity-items";
    }

    @GetMapping("/edit-workout/{workoutId}")
    public String getEditWorkoutView(@PathVariable int workoutId, Model model) {
        WorkoutDao workout = workoutService.getById(workoutId);
        model.addAttribute("workout", workout);
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!workout.getUserId().equals(userId)) {
            model.addAttribute("activeTab", "history");
            return "pages/errors/404";
        }
        model.addAttribute("isFinished", false);
        model.addAttribute("isEditable", true);
        return "pages/workouts/ongoing-workout-view";
    }

    @GetMapping("/finished-workout/{workoutId}")
    public String getJustFinishedWorkoutView(@PathVariable int workoutId, Model model) {
        WorkoutDao workout = workoutService.getById(workoutId);
        model.addAttribute("workout", workout);
        return "pages/workouts/finished-workout-view";
    }

    @PostMapping("/api/v1/workouts/start")
    public ResponseEntity<Void> startWorkout(@Valid @ModelAttribute WorkoutDto workoutDto) {
        int workoutId = workoutService.startWorkout(workoutDto);
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/workout/" + workoutId)
                .build();
    }

    @PatchMapping("/api/v1/workouts/finish/{workoutId}")
    public ResponseEntity<Void> finishWorkout(@PathVariable int workoutId) {
        workoutService.finishWorkout(workoutId);
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/finished-workout/" + workoutId)
                .build();
    }

    @DeleteMapping("/api/v1/workouts/{workoutId}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable int workoutId, @RequestParam (required = false) boolean redirect) {
        if (workoutService.delete(workoutId)) {
            var responseBuilder = ResponseEntity.status(204)
                    .header("HX-Trigger", "ShowToast")
                    .header("X-Message", "success: Workout deleted successfully!");

            if (redirect) {
                responseBuilder = responseBuilder.header("HX-Redirect", "/routines");
            }

            return responseBuilder.build();
        }

        return ResponseEntity.status(500)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: Failed to delete the workout, please try again later!")
                .build();
    }

}
