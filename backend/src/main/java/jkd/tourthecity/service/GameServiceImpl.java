package jkd.tourthecity.service;

import jkd.tourthecity.dto.GameDTO;
import jkd.tourthecity.model.Game;
import jkd.tourthecity.model.User;
import jkd.tourthecity.repository.GameRepository;
import jkd.tourthecity.repository.MapRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final MapRepository mapRepository;

    @Override
    public List<GameDTO> getAllGamesForUser(User user) {
        List<GameDTO> gameDTOList = new ArrayList<>();
        gameRepository.findAllByUserId(user).forEach(game -> {
            mapRepository.findById(game.getMapId().getId()).ifPresent(map -> {
                gameDTOList.add(new GameDTO(game, map));
            });
        });
        return gameDTOList;
    }
}
