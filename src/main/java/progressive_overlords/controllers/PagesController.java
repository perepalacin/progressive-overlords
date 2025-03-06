package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.services.WorkoutService;

@Controller
@RequiredArgsConstructor
public class PagesController {

    private final WorkoutService workoutService;

    @GetMapping("/")
    public String getHomePage (Model model) {
        return "pages/home";
    }

    @GetMapping("/sign-in")
    public String getSignInPage(Model model) {
        return "pages/sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        return "pages/sign-up";
    }

    @GetMapping("/workout/{workoutId}")
    public String getWorkoutPage(@PathVariable int workoutId, Model model) {
        WorkoutDao currentWorkout = workoutService.getWorkoutById(workoutId);
        model.addAttribute("workout", currentWorkout);
        return "pages/workouts/workout-view";
    }

}
