package progressive_overlords.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import progressive_overlords.entities.dao.SecurityUser;
import progressive_overlords.entities.dao.UserDao;
import progressive_overlords.repositories.UsersRepository;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao user = usersRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new SecurityUser(user);
    }

}
