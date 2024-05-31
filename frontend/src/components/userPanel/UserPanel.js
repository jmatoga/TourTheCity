import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { request } from "../axios_helper.js";
import Cookies from "js-cookie";
import "./userPanel.css"; // Importujemy plik CSS dla stylizacji

function UserPanel() {
  let navigate = useNavigate();

  const [user, setUserData] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8090/api/current-user`);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data = await response.json();
        setUserData(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <div className="main-container">
        <h2>{`${user.name} ${user.surname}`}</h2>
        <div className="signInForm">
          <h3>Witaj w Grze Terenowej!</h3>
          <h5>Zaloguj się, aby kontynuować</h5>
          <form className="form-container">
            <input
              className="text"
              type="email"
              placeholder="Adres email"
              value={user.email}
              // onChange={(e) => setEmail(e.target.value)}
              autoFocus
            />
            <input
              className="text-input"
              type="password"
              placeholder="Hasło"
              // value={userpassword}
              // onChange={(e) => setPassword(e.target.value)}
            />
            <div className="remember-me">
              <input type="checkbox" id="remember" name="remember" />
              <label htmlFor="remember">Zapamiętaj mnie</label>
            </div>
            {/* {info && <p className="error-message">{info}</p>} */}
            <button
              className="submit-btn"
              type="submit"
              id="SignInButton"
              name="SignInButton"
            >
              Sign In
            </button>
            <div className="forgot-password">
              <a href="#">Zapomniałeś hasła?</a>
            </div>
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

export default UserPanel;
