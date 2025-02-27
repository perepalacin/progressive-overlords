package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.SecurityUser;
import progressive_overlords.entities.dao.UserDao;
import progressive_overlords.entities.requests.SignUpRequest;
import progressive_overlords.repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao user = usersRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new SecurityUser(user);
    }

    public String registerUser(SignUpRequest signUpRequest) {
        if (userDetailsManager.userExists(signUpRequest.getUsername())) {
            return "User already exists!";
        }

        // Create user
        UserDetails user = User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles("USER") // Default role
                .build();

        userDetailsManager.createUser(user);

        return "User registered successfully!";
    }

}
