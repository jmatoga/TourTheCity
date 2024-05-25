import React, { useState } from 'react';
import './loginPanel.css'; // Importujemy plik CSS dla stylizacji

function LoginPanel() {
    return (
        <>
            <div className="main-container"> {/* Zastosowaliśmy nową klasę main-container */}
                {/* <div className="header">
                        <p>Gra Terenowa</p>
                    </div> */}
                    <div className="signInForm">
                <h3>Witaj w Grze Terenowej!</h3> {/* Usunięto komponent Typography */}
                <h5>Zaloguj się, aby kontynuować</h5>
                <form className="form-container"> {/* Zastosowaliśmy nową klasę form-container */}
                    <input
                        className="text-input"
                        type="email"
                        placeholder="Adres email"
                        autoFocus
                    />
                    <input
                        className="text-input"
                        type="password"
                        placeholder="Hasło"
                    />
                    <div className="remember-me">
                        <input type="checkbox" id="remember" name="remember" />
                        <label htmlFor="remember">Zapamiętaj mnie</label>
                    </div>
                    <button className="submit-btn" type="submit">Sign In</button> {/* Zmieniono na button zamiast Button */}
                    <div className="forgot-password">
                        <a href="#">Zapomniałeś hasła?</a>
                    </div>
                    <div className="footer">
                        <p>Nie masz konta? <a href="#">Zarejestruj się</a></p>
                    </div>
                </form>
                </div>
            </div>
        </>
    );
}

export default LoginPanel;
