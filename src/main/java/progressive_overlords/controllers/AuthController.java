package progressive_overlords.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.requests.AuthRequest;
import progressive_overlords.services.UserService;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String registerNewUser (@ModelAttribute AuthRequest signUpRequest) {
        userService.registerUser(signUpRequest);
        return "Successful register";
    }


    @PostMapping("/sign-in")
    public String loginUser (HttpServletResponse response, @ModelAttribute AuthRequest loginRequest) {
        String sessionToken = userService.login(loginRequest);
        Cookie sessionCookie = new Cookie("SESSION_ID", sessionToken);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setPath("/");

        response.addCookie(sessionCookie);
        return "pages/";
    }
}
