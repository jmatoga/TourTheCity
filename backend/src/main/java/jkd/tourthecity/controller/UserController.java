package jkd.tourthecity.controller;

import jkd.tourthecity.dto.UserDTO;
import jkd.tourthecity.dto.UserDetailsDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.mapper.UserMapper;
import jkd.tourthecity.model.User;
import jkd.tourthecity.security.payload.response.MessageResponse;
import jkd.tourthecity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/current-user")
    @PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    ResponseEntity<UserDTO> getCurrentUser() throws CurrentUserNotAuthenticatedException {
        User currentUser = userService.getCurrentUser();
        UserDTO dto = userMapper.mapToUserDTO(userService.getUserById(currentUser.getId()));
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/userDetails")
    @PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    ResponseEntity<MessageResponse> updateUserDetails(@RequestBody UserDetailsDTO userDTO) throws CurrentUserNotAuthenticatedException {
        userService.updateUserDetails(userDTO);
        return ResponseEntity.ok(new MessageResponse("User details updated successfully!"));
    }

    @GetMapping("/userRoles")
    ResponseEntity<Boolean> getUserRoles() throws CurrentUserNotAuthenticatedException {
        return ResponseEntity.ok(userService.isModeratorOrAdmin(userService.getCurrentUser().getId()));
    }
}
