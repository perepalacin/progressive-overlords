package progressive_overlords.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progressive_overlords.entities.requests.SignUpRequest;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/sign-up")
    public String registerNewUser (@RequestBody SignUpRequest signUpRequest) {
//        usersService.registerUser(signUpRequest);
        return "Successful register";
    }


    @PostMapping("/sign-in")
    public String loginUser (@RequestParam String username, @RequestParam String password) {
        System.out.println("hit this endpoint!");
//        if (usersService.loginUser(username, password)) {
//            return "pages/home";
//        }
        return "pages/sign-in";
    }
}
