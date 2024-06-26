import React, { useState, useEffect } from "react";
import "./AchievementsPanel.css"; // Importujemy plik CSS dla stylizacji

function AchievementsPanel() {
  const [achievements, setAchievementsData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8090/api/achievements`, {
          method: "GET",
          credentials: "include",
        });
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setAchievementsData(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="main-container">
      <h2>Twoje osiągnięcia:</h2>
      <div className="table-responsive">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Nazwa</th>
              <th scope="col">Opis</th>
              <th scope="col">Postęp</th>
            </tr>
          </thead>
          <tbody>
            {achievements.map((achievement, index) => (
              <tr className="achievement-row" key={index}>
                <td>{achievement.name}</td>
                <td>{achievement.description}</td>
                <td>
                  <progress
                    className={
                      achievement.progress < 30
                        ? "progress-bar bg-danger"
                        : achievement.progress < 70
                        ? "progress-bar bg-warning"
                        : "progress-bar bg-success"
                    }
                    value={achievement.progress}
                    max="100"
                  ></progress>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AchievementsPanel;
