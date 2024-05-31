package jkd.tourthecity.controller;

import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.mapper.UserMapper;
import jkd.tourthecity.model.User;
import jkd.tourthecity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/user/{userId}")
    //@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')" + "or hasRole('ROLE_PROJECT_MANAGER')")
    ResponseEntity<List<String>> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/current-user")
    //@PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    ResponseEntity<UserDTO> getCurrentUser() throws CurrentUserNotAuthenticatedException {
        return ResponseEntity.ok(userMapper.mapToUserDTO(userService.getCurrentUser()));
    }
}
