import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { request } from "../axios_helper.js";
import Cookies from "js-cookie";
import "./loginPanel.css"; // Importujemy plik CSS dla stylizacji

function LoginPanel({ onLogin }) {
  let navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [info, setInfo] = useState("");

  const handleLogin = (event) => {
    event.preventDefault();
    request("post", "http://localhost:8090/api/auth/login", {
      email: email,
      password: password,
    })
      .then((response) => {
        Cookies.set("accessToken", response.data.token);
        setInfo("Zalogowano pomyślnie!");
        onLogin(true); // Aktualizuj stan logowania
        navigate("/");
      })
      .catch((error) => {
        setInfo("Niepoprawny email lub hasło");
        console.log(error);
      });
  };

  return (
    <>
      <div className="main-container">
        <div className="signInForm">
          <h3>Witaj w Grze Terenowej!</h3>
          <h5>Zaloguj się, aby kontynuować</h5>
          <form className="form-container" onSubmit={handleLogin}>
            <input
              className="text-input"
              type="email"
              placeholder="Adres email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              autoFocus
            />
            <input
              className="text-input"
              type="password"
              placeholder="Hasło"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <div className="remember-me">
              <input type="checkbox" id="remember" name="remember" />
              <label htmlFor="remember">Zapamiętaj mnie</label>
            </div>
            {info && <p className="error-message">{info}</p>}
            <button
              className="submit-btn"
              type="submit"
              id="SignInButton"
              name="SignInButton"
            >
              Zaloguj się
            </button>
            {/* <div className="forgot-password">
              <a href="#">Zapomniałeś hasła?</a>
            </div> */}
            <div className="footer">
              <p>
                Nie masz konta?
                <Link to="/register" className="register-link">
                  Zarejestruj się
                </Link>
              </p>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default LoginPanel;
