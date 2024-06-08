package jkd.tourthecity.repository;

import jkd.tourthecity.dto.AchievementsDTO;
import jkd.tourthecity.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, String> {
    @Query(value = "SELECT * FROM achievements WHERE accomplishment_id = ?1", nativeQuery = true)
    Achievement findByAccomplishmentId(String id);

//    @Query(value = "SELECT * from achievements as ach " +
//                           "LEFT JOIN accomplishment as acc on ach.accomplishment_id=acc.id " +
//                           "where acc.user_id = ?1", nativeQuery = true)
//    List<AchievementsDTO> getAchievementsList(String userId);
//
//
//    List<Achievement> findAllByUserId(String userId);
}
