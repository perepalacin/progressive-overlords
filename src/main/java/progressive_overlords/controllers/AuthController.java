package progressive_overlords.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import progressive_overlords.entities.requests.AuthRequest;
import progressive_overlords.entities.responses.GenericResponse;
import progressive_overlords.services.UserService;

import java.io.IOException;

@Controller
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<GenericResponse> registerNewUser (@ModelAttribute AuthRequest signUpRequest) {
        userService.registerUser(signUpRequest);
        return new ResponseEntity<>(new GenericResponse("Successful register"), HttpStatus.CREATED);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<Void> loginUser(HttpServletResponse response, @ModelAttribute AuthRequest loginRequest) {
        String sessionToken = userService.login(loginRequest);

        Cookie sessionCookie = new Cookie("SESSION_ID", sessionToken);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "SESSION_ID=" + sessionToken + "; Path=/; HttpOnly; Secure");
        headers.add(HttpHeaders.LOCATION, "/");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    @PostMapping("/logout")
    public String logout (HttpServletResponse response) throws IOException {
        System.out.println("Logging out!");
        Cookie sessionCookie = new Cookie("SESSION_ID", null);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        return "redirect:/sign-in";
    }
}
