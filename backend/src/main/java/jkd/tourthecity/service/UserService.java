package jkd.tourthecity.service;

import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.model.User;

import java.util.List;

public interface UserService {
    User getCurrentUser() throws CurrentUserNotAuthenticatedException;

    List<User> getAllUsers();

    void registerUser(User user);

    User getUserById(String id);

    void updateUserDetails(UserDetailsDTO userDTO) throws CurrentUserNotAuthenticatedException;

    boolean isModeratorOrAdmin(String userId);
}
