package jkd.tourthecity.service;

import jakarta.transaction.Transactional;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.response.MessageResponse;
import jkd.tourthecity.validation.UserRoleValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import jkd.tourthecity.exception.*;
import jkd.tourthecity.repository.*;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRoleValidator userRoleValidator;
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Override
    public User getCurrentUser() throws CurrentUserNotAuthenticatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userRepository.findByEmail(authentication.getName())
                           .orElseThrow(() -> new RuntimeException("Current user not found!"));
        } else throw new CurrentUserNotAuthenticatedException();
    }

    @Override
    public List<String> getUserById(String userId) {
        return userRepository.findAllByUserId(userId);
               // .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
