import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./HistoryPanel.css"; // Importujemy plik CSS dla stylizacji

function HistoryPanel() {
  let navigate = useNavigate();

  const [games, setGamesData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8090/api/games`, {
          method: "GET",
          credentials: "include",
        });
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setGamesData(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="main-container">
      <h2>Twoja historia gier:</h2>
      <div className="table-responsive">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Lp.</th>
              <th scope="col">Nazwa</th>
              <th scope="col">Lokalizacja</th>
              <th scope="col">Trudność</th>
              <th scope="col">Status</th>
              <th scope="col">Punkty</th>
              <th scope="col">Start</th>
              <th scope="col">Koniec</th>
            </tr>
          </thead>
          <tbody>
            {games.map((game, index) => (
              <tr className="achievement-row" key={index}>
                <td>{index + 1}</td>
                <td>{game.name}</td>
                <td>{game.location}</td>
                <td>{game.difficulty}</td>
                <td>{game.gameStatus}</td>
                <td>{game.points}</td>
                <td>{game.startTime}</td>
                <td>{game.endTime ? game.endTime : "-------"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default HistoryPanel;
