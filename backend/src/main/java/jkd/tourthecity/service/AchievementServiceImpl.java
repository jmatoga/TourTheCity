package jkd.tourthecity.service;

import jkd.tourthecity.dto.AchievementsDTO;
import jkd.tourthecity.model.Accomplishment;
import jkd.tourthecity.model.Achievement;
import jkd.tourthecity.model.User;
import jkd.tourthecity.repository.AccomplishmentRepository;
import jkd.tourthecity.repository.AchievementRepository;
import jkd.tourthecity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final AccomplishmentRepository accomplishmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<AchievementsDTO> getAchievementsList(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<AchievementsDTO> achievementsList = new ArrayList<>();
        List<Accomplishment> accomplishment = accomplishmentRepository.findAllByUserId(user);
        accomplishment.forEach(acc -> {
            Achievement achievement = achievementRepository.findByAccomplishmentId(acc.getId());
            achievementsList.add(new AchievementsDTO(achievement.getName(), achievement.getDescription(), acc.getProgress()));
        });

        return achievementsList;
    }
}
