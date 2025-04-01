package progressive_overlords.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

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
        model.addAttribute("tabName", "Home");
        return "pages/home";
    }
}
