package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.services.WorkoutsService;

import java.util.UUID;


@Controller
@RequestMapping("${api.prefix}/workouts")
@RequiredArgsConstructor
public class WorkoutsController {

    @Value("${master.userId}")
    private String masterUserId;
    private final WorkoutsService workoutsService;

    @PostMapping("/start")
    public String startNewWorkout (@RequestParam (required = false) Integer templateId, Model model) {
        WorkoutDao newWorkout = workoutsService.startWorkout(templateId, UUID.fromString(masterUserId));
        model.addAttribute("workout", newWorkout);
        return "responses/workout/start-workout";
    }

    @PostMapping("/end/{workoutId}")
    public String endWorkout (@PathVariable int workoutId, Model model) {
        WorkoutDao newWorkout = workoutsService.endWorkout(workoutId, UUID.fromString(masterUserId));
        model.addAttribute("workout", newWorkout);
        return "responses/workout/start-workout";
    }




//    @GetMapping
//    public String getLastUserWorkouts (@RequestParam int limit, @RequestParam int offset) {
////        WorkoutDao workoutDao = workoutsService.
//    }

}
