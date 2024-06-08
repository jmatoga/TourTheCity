package jkd.tourthecity.controller;

import jkd.tourthecity.dto.AchievementsDTO;
import jkd.tourthecity.service.AchievementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class AchievementsController {
    private final AchievementService achievementService;
    @GetMapping("/achievements/{userId}")
    public List<AchievementsDTO> getAchievemetnsList(@PathVariable String userId) {
        return achievementService.getAchievementsList(userId);
    }

}
