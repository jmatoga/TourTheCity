package jkd.tourthecity.service;

import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.model.ERole;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getCurrentUser(String token) throws CurrentUserNotAuthenticatedException;

    List<String> getUserById(String userId);

    List<User> getAllUsers();

    void registerUser(User user);

    User getUser(String id);

    void updateUserDetails(UserDetailsDTO userDTO);

    boolean isModeratorOrAdmin(String userId);
}
