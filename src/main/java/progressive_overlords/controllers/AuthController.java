package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import progressive_overlords.entities.requests.SignUpRequest;
import progressive_overlords.services.CustomUserService;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserService usersService;

    @PostMapping("/sign-up")
    public String registerNewUser (@RequestBody SignUpRequest signUpRequest) {
        usersService.registerUser(signUpRequest);
        return "Successful register";
    }
}
