package jkd.tourthecity.mapper;

import jkd.tourthecity.dto.GameDTO;
import jkd.tourthecity.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(imports = String.class, componentModel = "spring")
public abstract class GameMapper {

    @Named("mapToGamesDTO")
    public abstract List<GameDTO> mapToGamesDTO(List<Game> games);
}
