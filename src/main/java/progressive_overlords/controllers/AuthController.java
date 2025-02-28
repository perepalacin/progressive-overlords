package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.requests.SignUpRequest;
import progressive_overlords.services.UserService;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String registerNewUser (@ModelAttribute SignUpRequest signUpRequest) {
        userService.registerUser(signUpRequest);
        return "Successful register";
    }


    @PostMapping("/sign-in")
    public String loginUser (@ModelAttribute SignUpRequest signUpRequest) {
//        if (usersService.loginUser(username, password)) {
//            return "pages/home";
//        }
        return "pages/sign-in";
    }
}
