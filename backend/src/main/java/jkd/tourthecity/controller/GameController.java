package jkd.tourthecity.controller;

import jkd.tourthecity.dto.GameDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.mapper.GameMapper;
import jkd.tourthecity.model.Game;
import jkd.tourthecity.model.User;
import jkd.tourthecity.service.GameService;
import jkd.tourthecity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class GameController {
    private final GameService gameService;
    private final UserService userService;
    private final GameMapper gameMapper;

    @GetMapping("/games")
    @PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    public ResponseEntity<List<GameDTO>> getAllGamesForUser() throws CurrentUserNotAuthenticatedException {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(gameService.getAllGamesForUser(currentUser));
    }
}
