import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { request } from "../axios_helper.js";
import Cookies from "js-cookie";
import "./userPanel.css"; // Importujemy plik CSS dla stylizacji
import axios from "axios";
import { Button } from "react-bootstrap";

function UserPanel({ onLogin }) {
  let navigate = useNavigate();
  const [userName, setUserName] = useState("");

  const [user, setUserData] = useState({
    name: "",
    surname: "",
    email: "",
    password: "",
    points: "",
  });

  const [userDTO, setUserDTOData] = useState({
    id: Cookies.get("userId"),
    name: "",
    surname: "",
    email: "",
    password: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8090/api/current-user/${Cookies.get("userId")}`
        );
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setUserData(data);
        setUserName(`${data.name} ${data.surname}`);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUserData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setUserDTOData(user);
    axios
      .put(`http://localhost:8090/api/userDetails`, userDTO)
      .then((res) => {
        alert("Dane zmienione pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        alert("Wystąpił błąd podczas zmiany danych!");
        console.log(error);
      });
  };

  const handleLogout = (e) => {
    e.preventDefault();
    const allCookies = Cookies.get();
    for (let cookie in allCookies) {
      Cookies.remove(cookie);
    }
    onLogin(false);
    navigate("/");
  };

  return (
    <>
      <div className="main-container">
        <h2>Witaj {`${userName}!`}</h2>
        <div className="signInForm">
          <h5>Twoje dane:</h5>
          <form className="form-container">
            <p>
              Punkty: <b>{`${user.points}`}</b>
            </p>
            <input
              className="text-input"
              name="name"
              placeholder="Imie"
              value={user.name}
              onChange={handleInputChange}
              autoFocus
            />
            <input
              className="text-input"
              name="surname"
              placeholder="Naziwsko"
              value={user.surname}
              onChange={handleInputChange}
              autoFocus
            />
            <input
              className="text-input"
              name="email"
              placeholder="Adres email"
              value={user.email}
              onChange={handleInputChange}
              autoFocus
            />
            <input
              className="text-input"
              type="password"
              name="password"
              placeholder="Hasło"
              onChange={handleInputChange}
            />
            {/* {info && <p className="error-message">{info}</p>} */}
            <button
              className="submit-btn"
              type="submit"
              id="SaveChangesButton"
              name="SaveChangesButton"
              onSubmit={handleSubmit}
            >
              Zapisz zmiany
            </button>
            <div className="mt-3">
              <Button
                variant="danger"
                className="submit-btn"
                type="logout"
                id="LogoutButton"
                name="LogoutButton"
                onClick={handleLogout}
              >
                Wyloguj się
              </Button>
            </div>
            <div className="footer"></div>
          </form>
        </div>
      </div>
    </>
  );
}

export default UserPanel;
