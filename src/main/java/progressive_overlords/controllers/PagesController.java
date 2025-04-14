package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import progressive_overlords.entities.dao.PublicUserDao;
import progressive_overlords.services.FriendsService;
import progressive_overlords.services.UserService;
import progressive_overlords.services.WorkoutSummaryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PagesController {

    private final FriendsService friendsService;
    private final UserService userService;

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
        model.addAttribute("ownUserDetails", ownUserDetails);
        model.addAttribute("userSuggestionList", userSuggestionList);
        model.addAttribute("tabName", "Home");
        return "pages/home";
    }
}
