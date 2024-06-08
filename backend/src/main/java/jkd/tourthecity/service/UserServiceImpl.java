package jkd.tourthecity.service;

import jakarta.transaction.Transactional;
import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.model.User;
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

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                       .orElseThrow(() -> new RuntimeException("User with ID: " + userId + " not found!"));
    }

    @Override
    public void updateUserDetails(UserDetailsDTO userDTO) {
        User user = getUserById(userDTO.id());
        if(userDTO.name() != null && !userDTO.name().isEmpty())
            user.setName(userDTO.name());
        if(userDTO.surname() != null && !userDTO.surname().isEmpty())
            user.setSurname(userDTO.surname());
        if(userDTO.email() != null && !userDTO.email().isEmpty())
            user.setEmail(userDTO.email());
        if(userDTO.password() != null && !userDTO.password().isEmpty())
            user.setPassword(encoder.encode(userDTO.password()));
        userRepository.save(user);
    }

    @Override
    public boolean isModeratorOrAdmin(String userId) {
        User user = getUserById(userId);
        return userRoleValidator.isModeratorOrAdmin(user);
    }

}
