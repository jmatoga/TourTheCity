package jkd.tourthecity.service;

import jakarta.transaction.Transactional;
import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.model.ERole;
import jkd.tourthecity.model.RefreshToken;
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
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRoleValidator userRoleValidator;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private final RefreshTokenRepository refreshTokenRepository;

//    @Override
//    public User getCurrentUser() throws CurrentUserNotAuthenticatedException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            System.out.println(authentication.getName());
//            System.out.println(authentication.getPrincipal());
//            return userRepository.findByEmail(authentication.getName())
//                           .orElseThrow(() -> new RuntimeException("Current user not found!"));
//        } else throw new CurrentUserNotAuthenticatedException();
//    }

    @Override
    public User getCurrentUser(String token) throws CurrentUserNotAuthenticatedException {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        if (refreshToken.isPresent()) {
            return refreshToken.get().getUser();
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

    @Override
    public User getUser(String id) {
        return userRepository.findById(id)
                       .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found!"));
    }

    @Override
    public void updateUserDetails(UserDetailsDTO userDTO) {
        User user = userRepository.findById(userDTO.id())
                       .orElseThrow(() -> new RuntimeException("User with ID: " + userDTO.id() + " not found!"));
        if(userDTO.name() != null)
            user.setName(userDTO.name());
        if(userDTO.surname() != null)
            user.setSurname(userDTO.surname());
        if(userDTO.email() != null)
            user.setEmail(userDTO.email());
        if(userDTO.password() != null)
            user.setPassword(encoder.encode(userDTO.password()));
        userRepository.save(user);
    }

    @Override
    public boolean isModeratorOrAdmin(String userId) {
        User user = userRepository.findById(userId)
                       .orElseThrow(() -> new RuntimeException("User with ID: " + userId + " not found!"));
        return userRoleValidator.isModeratorOrAdmin(user);
    }

}
