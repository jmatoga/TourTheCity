package jkd.tourthecity.service;

import jkd.tourthecity.dto.GameDTO;
import jkd.tourthecity.model.User;

import java.util.List;

public interface GameService {
    List<GameDTO> getAllGamesForUser(User user);
}
