package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import progressive_overlords.entities.dao.ExerciseDao;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.entities.dao.WorkoutSummaryDao;
import progressive_overlords.repositories.WorkoutSummaryRepository;
import progressive_overlords.services.ExercisesService;
import progressive_overlords.services.FriendsService;
import progressive_overlords.services.UserService;
import progressive_overlords.services.WorkoutSummaryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PagesController {

    private final FriendsService friendsService;
    private final UserService userService;
    private final WorkoutSummaryService workoutSummaryService;
    private final ExercisesService exercisesService;

    @GetMapping("/sign-in")
    public String getLoginPage (Model model) {
        model.addAttribute("tabName", "Login");
        return "pages/sign-in";
    }

    @GetMapping("/sign-up")
    public String getRegisterPage (Model model) {
        model.addAttribute("tabName", "Register");
        return "pages/sign-up";
    }

    @GetMapping("/")
    public String getHomeScreen (Model model) {
        List<PublicUserDao> userSuggestionList = friendsService.getRecommendedFriends();
        PublicUserDao ownUserDetails = userService.getOwnUserDetails();
        List<WorkoutSummaryDao> feedActivity =  workoutSummaryService.getFriendsActivitySummary(0);
        model.addAttribute("ownUserDetails", ownUserDetails);
        model.addAttribute("feedActivity", feedActivity);
        model.addAttribute("userSuggestionList", userSuggestionList);
        model.addAttribute("tabName", "Home");
        return "pages/home";
    }

    @GetMapping("/records")
    public String getStatsPage (Model model) {
        List<ExerciseDao> exercises = exercisesService.getExercises(0, "");
        model.addAttribute("exercises", exercises);
        return "pages/records/records-page";
    }
}
