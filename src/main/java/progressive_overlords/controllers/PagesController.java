package progressive_overlords.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/login")
    public String getLoginPage (Model model) {
        model.addAttribute("tabName", "Login");
        return "pages/sign-in";
    }

    @GetMapping("/register")
    public String getRegisterPage (Model model) {
        model.addAttribute("tabName", "Register");
        return "pages/sign-up";
    }
}
