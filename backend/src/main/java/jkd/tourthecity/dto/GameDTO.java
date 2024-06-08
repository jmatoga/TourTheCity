package jkd.tourthecity.dto;

import jkd.tourthecity.model.Game;
import jkd.tourthecity.model.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private String startTime;
    private String endTime;
    private Integer gameStatus;
    private Integer points;
    private String location;
    private String name;
    private Integer difficulty;

    public GameDTO(Game game, Map map) {
        this.startTime = game.getStartTime().toString();
        this.endTime = game.getEndTime().toString();
        this.gameStatus = game.getGameStatus();
        this.points = game.getPoints();
        this.location = map.getLocation();
        this.name = map.getName();
        this.difficulty = map.getDifficulty();
    }
}
