package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.UserDao;
import progressive_overlords.entities.requests.SignUpRequest;
import progressive_overlords.exceptions.UsernameAlreadyExistsExcpetion;
import progressive_overlords.repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;

    public String registerUser (SignUpRequest signUpRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signUpRequest.getUsername().toLowerCase());
        if (userDetails != null) {
            throw new UsernameAlreadyExistsExcpetion("This username is already taken, please try another one.");
        }

        usersRepository.save(UserDao.builder().username(signUpRequest.getUsername()).password(encoder.encode(signUpRequest.getPassword())).build());
        return "Account created";
    }
}
