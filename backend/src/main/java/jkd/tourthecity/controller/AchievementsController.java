package jkd.tourthecity.controller;

import jkd.tourthecity.dto.AchievementsDTO;
import jkd.tourthecity.exception.CurrentUserNotAuthenticatedException;
import jkd.tourthecity.model.User;
import jkd.tourthecity.service.AchievementService;
import jkd.tourthecity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class AchievementsController {
    private final AchievementService achievementService;
    private final UserService userService;

    @GetMapping("/achievements")
    @PreAuthorize("hasRole('ROLE_USER')" + "or hasRole('ROLE_MODERATOR')" + "or hasRole('ROLE_ADMIN')" )
    public List<AchievementsDTO> getAchievemetnsList() throws CurrentUserNotAuthenticatedException {
        User currentUser = userService.getCurrentUser();
        return achievementService.getAchievementsList(currentUser.getId());
    }

}
