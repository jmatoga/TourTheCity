package jkd.tourthecity.service;

import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getCurrentUser() throws CurrentUserNotAuthenticatedException;

    List<String> getUserById(String userId);

    List<User> getAllUsers();

    void registerUser(User user);
}
