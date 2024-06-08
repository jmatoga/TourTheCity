package jkd.tourthecity.controller;

import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.mapper.UserMapper;
import jkd.tourthecity.model.ERole;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.response.MessageResponse;
import jkd.tourthecity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
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

    @GetMapping("/current-user/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    ResponseEntity<UserDTO> getCurrentUser(@PathVariable String id) {
        User currentUser = userService.getUser(id);
        UserDTO dto = userMapper.mapToUserDTO(userService.getUser(currentUser.getId()));
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/userDetails")
    ResponseEntity<MessageResponse> updateUserDetails(@RequestBody UserDetailsDTO userDTO) {
        userService.updateUserDetails(userDTO);
        return ResponseEntity.ok(new MessageResponse("User details updated successfully!"));
    }

    @GetMapping("/userRoles/{userId}")
    ResponseEntity<Boolean> getUserRoles(@PathVariable String userId) {
        return ResponseEntity.ok(userService.isModeratorOrAdmin(userId));
    }
}
