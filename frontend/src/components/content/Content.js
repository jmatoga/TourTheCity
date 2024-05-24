import React, { useState } from "react";
import './content.css';

const GameSelector = ({ onMapChange }) => {
  const [gameMode, setGameMode] = useState("Swobodna");
  const [selectedMap, setSelectedMap] = useState("");
  const [gameModeDescription, setGameModeDescription] = useState(<><h5>Jesteś w trybie swobodnego zwiedzania.</h5></>);
  const [seeMoreLabel, setSeeMoreLabel] = useState("Zwiń zawartość");

  const handleSeeMoreLabel = () => {
    setSeeMoreLabel(seeMoreLabel === 'Zobacz więcej' ? 'Zwiń zawartość' : 'Zobacz więcej');
  }

  const handleGameModeChange = (e) => {
    const selectedMode = e.target.value;
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
    setGameMode(selectedMode);
    setSelectedMap(""); // Resetowanie wybranej mapy po zmianie trybu gry
  };

  const handleMapChange = (e) => {
    const selectedMapValue = e.target.value;
    setSelectedMap(selectedMapValue);
    onMapChange(selectedMapValue); // Informuje rodzica o zmianie mapy
  };

  return (
    <div>
      <label htmlFor="checkbox" id="hamburger-label" onClick={handleSeeMoreLabel}>
        <div className="see-more-button">
          {seeMoreLabel}
        </div>
      </label>
      <input type="checkbox" id="checkbox"/>

      <div className="gamemode-content">
        <div className="gamemode-description">
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
              <option value="monumentsTour">Trasa zabytków</option>
              <option value="universityTour">Trasa uczelni</option>
              <option value="moundTour">Trasa kopców</option>
            </select>
          </div>
        )}

        <br/><br/>  
      </div>
    </div>
  );
};

export default GameSelector;
