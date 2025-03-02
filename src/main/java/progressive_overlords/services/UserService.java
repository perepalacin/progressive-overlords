package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.UserDao;
import progressive_overlords.entities.requests.AuthRequest;
import progressive_overlords.exceptions.UsernameAlreadyExistsExcpetion;
import progressive_overlords.repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;

    public String registerUser (AuthRequest signUpRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signUpRequest.getUsername().toLowerCase());
        if (userDetails != null) {
            throw new UsernameAlreadyExistsExcpetion("This username is already taken, please try another one.");
        }

        usersRepository.save(UserDao.builder().username(signUpRequest.getUsername()).password(encoder.encode(signUpRequest.getPassword())).build());
        return "Account created";
    }

    public String login(AuthRequest loginRequest){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authentication = authManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDao userDao = usersRepository.findByUsername(loginRequest.getUsername().toLowerCase());
        return SessionsService.generateToken(userDao);
    }
}
