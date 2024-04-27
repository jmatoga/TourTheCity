import React, { useState } from "react";
import './content.css';

const GameSelector = () => {
  const [gameMode, setGameMode] = useState("Swobodna"); //hook stanu (useState) do definicji zmiennej gameMode (domyślnie Swobodna)
  const [selectedMap, setSelectedMap] = useState("");
  const [gameModeDescription, setGameModeDescription] = useState(<><h5>Jesteś w trybie swobodnego zwiedzania.</h5></>);
  const handleGameModeChange = (e) => {

    const selectedMode = e.target.value;

  // Aktualizacja opisu trybu gry w zależności od wybranego trybu
    switch(selectedMode) {
      case "Swobodna":
        setGameModeDescription(
          <>
            <h5>Jesteś w trybie swobodnego zwiedzania.</h5>
          </>
        );
        break;
      case "Wyzwanie":
        setGameModeDescription(
          <>
            <h5>Jesteś w trybie wyzwania.</h5>
          </>
          
        );
        break;
      default:
        setGameModeDescription("");
    }
    setGameMode(e.target.value);
    setSelectedMap(""); // Resetowanie wybranej mapy po zmianie trybu gry
  };

  const handleMapChange = (e) => {
    setSelectedMap(e.target.value);
  };

  return (
    <div>
      <label for="checkbox" id="hamburger-label">
        <div className="see-more-button">
          Zobacz więcej
        </div>
      </label>
      <input type="checkbox" id="checkbox"/>

      

      <div className="gamemode-content">
        <div className="gamemode-description">
          <br/>
          {gameModeDescription}
          Aby dostosować wypełnij poniższe pola.
        </div>
        <br/><br/>
        <div>
          <div className="form-check">
            <input
              type="radio"
              id="swobodna"
              name="gameMode"
              className="form-check-input"
              value="Swobodna"
              checked={gameMode === "Swobodna"}
              onChange={handleGameModeChange}
            />
            <label className="form-check-label" htmlFor="swobodna">Swobodna</label>
          </div>

          <div className="form-check">
            <input
              type="radio"
              id="wyzwanie"
              name="gameMode"
              className="form-check-input"
              value="Wyzwanie"
              checked={gameMode === "Wyzwanie"}
              onChange={handleGameModeChange}
            />
            <label className="form-check-label" htmlFor="wyzwanie">Wyzwanie</label>
          </div>
        </div>

        <br/>

        {gameMode === "Wyzwanie" && (
          <div>
            <label htmlFor="mapSelect">Wybierz mapę: <p/></label>
            <select className="form-select" id="mapSelect" value={selectedMap} onChange={handleMapChange}>
              <option value="">Wybierz mapę</option>
              <option value="map1">Mapa 1</option>
              <option value="map2">Mapa 2</option>
              <option value="map3">Mapa 3</option>
            </select>
          </div>
          
        )}

      <br/><br/>  
      </div>
    </div>
  );
};

export default GameSelector;
