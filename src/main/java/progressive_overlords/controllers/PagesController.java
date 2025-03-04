package progressive_overlords.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

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
    public String getWorkoutPage(Model model) {
        model.addAttribute("workout", null);
        return "pages/workouts/workout-view";
    }

}
