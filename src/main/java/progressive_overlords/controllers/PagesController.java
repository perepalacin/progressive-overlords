package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.services.WorkoutService;

import java.util.List;

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

    @GetMapping("/templates")
    public String getUsersTemplates(Model model) {
        List<WorkoutDao> templates = workoutService.getUserTemplates();
        model.addAttribute("templates", templates);
        return "pages/workouts/user-templates";
    }

    @GetMapping("/create-template")
    public String getCreateTemplateView(Model model) {
        model.addAttribute("template", null);
        return "pages/workouts/create-template";
    }

    @GetMapping("/edit-template/{templateId}")
    public String getEditTemplateView(@PathVariable int templateId, Model model) {
        WorkoutDao template = workoutService.getUserTemplateById(templateId);
        model.addAttribute("template", template);
        return "pages/workouts/create-template";
    }

    @GetMapping("/workout/{workoutId}")
    public String getWorkoutPage(@PathVariable int workoutId, Model model) {
        WorkoutDao currentWorkout = workoutService.getWorkoutById(workoutId);
        model.addAttribute("workout", currentWorkout);
        return "pages/workouts/workout-view";
    }

    //TODO: This endpoint is only meant for when the user submits a workout! Otherwise, we will hit the /workout/workoutId endpoint!
    //this one should only show the actual sets! not the sets from the template!!
    @GetMapping("/workout-finished/{workoutId}")
    public String getFinishWorkoutPage(@PathVariable int workoutId, Model model) {
        WorkoutDao currentWorkout = workoutService.getWorkoutById(workoutId);
        model.addAttribute("workout", currentWorkout);
        return "pages/workouts/finish-workout-view";
    }

}
