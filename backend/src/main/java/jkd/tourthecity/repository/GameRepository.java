package jkd.tourthecity.repository;

import jkd.tourthecity.model.Game;
import jkd.tourthecity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {

    List<Game> findAllByUserId(User userId);
}
