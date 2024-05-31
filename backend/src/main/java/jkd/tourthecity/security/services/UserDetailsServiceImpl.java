package jkd.tourthecity.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jkd.tourthecity.exception.LoginAttemptException;
import jkd.tourthecity.model.User;
import jkd.tourthecity.repository.UserRepository;
import jkd.tourthecity.security.LoginAttemptService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (loginAttemptService.isBlocked(username)) {
            Integer blockedFor = loginAttemptService.isBlockedFor(username);
            throw new LoginAttemptException(" Login blocked for: " + blockedFor + "min.");
        }

        User user = userRepository.findByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}