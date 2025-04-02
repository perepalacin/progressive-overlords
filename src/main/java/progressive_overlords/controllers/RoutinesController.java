package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.WorkoutDto;
import progressive_overlords.services.RoutinesService;

@Controller
@RequiredArgsConstructor
public class RoutinesController {

    private final RoutinesService routinesService;

    @GetMapping("/create-routine")
    public String getCreateRoutineView (Model model) {
        model.addAttribute("routine", null);
        return "pages/routines/create-edit-routine";
    }

    @GetMapping("/create-routine/{routineId}")
    public String getCreateRoutineView (@PathVariable int routineId, Model model) {
        WorkoutDao routine = routinesService.getById(routineId);
        model.addAttribute("routine", routine);
        return "pages/routines/create-edit-routine";
    }

    @GetMapping("/routines/{routineId}")
    public String getRoutineView (@PathVariable int routineId, Model model) {
        WorkoutDao routine = routinesService.getById(routineId);
        model.addAttribute("routine", routine);
        return "pages/routine-view";
    }

    @PostMapping("/api/v1/routines")
    public ResponseEntity<Void> saveRoutine (@ModelAttribute WorkoutDto workoutDto) {
        WorkoutDao routine = routinesService.saveRoutine(workoutDto);
        System.out.println("Routine saved successfully");
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/routine/" + routine.getId())
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "success: Routine created successfully!")
                .build();
    }

    @PatchMapping("/api/v1/routines")
    public ResponseEntity<Void> editRoutine (@ModelAttribute WorkoutDto workoutDto) {
        try {
            WorkoutDao routine = routinesService.editRoutine(workoutDto);
        System.out.println("Routine saved successfully");
        return ResponseEntity.status(303)
                .header("HX-Redirect", "/routine/" + routine.getId())
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "success: Routine created successfully!")
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/api/v1/routines/{routineId}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable int routineId) {
        if (routinesService.delete(routineId)) {
            return ResponseEntity.status(201)
                    .header("HX-Trigger", "ShowToast")
                    .header("X-Message", "success: Routine deleted successfully!")
                    .build();
        }
        return ResponseEntity.status(500)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: Failed to delete the routine, please try again later!")
                .build();
    }


}
