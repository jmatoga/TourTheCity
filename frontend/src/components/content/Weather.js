import React, { useState, useEffect } from "react";
import { Line } from "react-chartjs-2";
import "bootstrap/dist/css/bootstrap.min.css";
import "./content.css";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TimeScale,
} from "chart.js";
import "chartjs-adapter-date-fns";

// Register Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TimeScale
);

const WeatherDisplay = () => {
  const [weatherData, setWeatherData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const params = {
        latitude: 52,
        longitude: 20,
        daily: ["temperature_2m_max", "temperature_2m_min", "rain_sum"],
        timezone: "auto",
        forecast_days: 7,
      };

      const url = `https://api.open-meteo.com/v1/forecast?${new URLSearchParams(
        params
      )}`;
      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setWeatherData(data.daily);
      } catch (error) {
        console.error("Error fetching weather data:", error);
      }
    };

    fetchData();
  }, []); // Fetch data only once when the component is mounted

  if (!weatherData) {
    return <div>Loading...</div>;
  }

  const { time, temperature_2m_max, temperature_2m_min, rain_sum } =
    weatherData;

  if (!time || !temperature_2m_max || !temperature_2m_min || !rain_sum) {
    console.error("Weather data is missing expected properties:", weatherData);
    return <div>Error: Weather data is missing expected properties</div>;
  }

  const formattedTime = time.map((t) => new Date(t).toISOString());

  const data = {
    labels: formattedTime,
    datasets: [
      {
        label: "Temp max",
        data: temperature_2m_max,
        fill: false,
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.5)",
        tension: 0.1,
      },
      {
        label: "Temp min",
        data: temperature_2m_min,
        fill: false,
        borderColor: "rgb(54, 162, 235)",
        backgroundColor: "rgba(54, 162, 235, 0.5)",
        tension: 0.1,
      },
      {
        label: "Suma opadów",
        data: rain_sum,
        fill: false,
        borderColor: "rgb(75, 192, 192)",
        backgroundColor: "rgba(75, 192, 192, 0.5)",
        tension: 0.1,
        yAxisID: "y1",
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      tooltip: {
        callbacks: {
          label: function (context) {
            let label = context.dataset.label || "";
            if (label) {
              label += ": ";
            }
            label += context.parsed.y;
            if (context.dataset.label === "Rain Sum") {
              label += " mm";
            } else {
              label += " °C";
            }
            return label;
          },
        },
      },
    },
    scales: {
      x: {
        type: "time",
        time: {
          unit: "day",
        },
        // title: {
        //   display: true,
        //   text: "Data",
        // },
      },
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: "Temperatura (°C)",
        },
      },
      y1: {
        beginAtZero: true,
        position: "right",
        title: {
          display: true,
          text: "Opady (mm)",
        },
        grid: {
          drawOnChartArea: false,
        },
      },
    },
  };

  return (
    <div className="container chart-container">
      <h2>Prognoza Pogody</h2>
      <div className="row">
        <div className="col">
          <Line data={data} options={options} />
        </div>
      </div>
    </div>
  );
};

export default WeatherDisplay;
