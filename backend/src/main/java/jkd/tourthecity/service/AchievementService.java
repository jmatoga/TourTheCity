package jkd.tourthecity.service;

import jkd.tourthecity.dto.AchievementsDTO;

import java.util.List;

public interface AchievementService {
     List<AchievementsDTO> getAchievementsList(String userId);
}
