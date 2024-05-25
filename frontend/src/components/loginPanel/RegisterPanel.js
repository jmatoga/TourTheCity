import React from 'react';
import { Link } from 'react-router-dom';
import './loginPanel.css'; // Importujemy plik CSS dla stylizacji

function RegisterPage() {
    return (
        <>
            <div className="main-container"> {/* Zastosowano nową klasę main-container */}
                {/* <div className="header">
                        <p>Gra Terenowa</p>
                    </div> */}
                <div className="signInForm">
                    <h3>Witaj w Grze Terenowej!</h3> {/* Usunięto komponent Typography */}
                    <h5>Zarejestruj się, aby kontynuować</h5>
                    <form className="form-container"> {/* Zastosowano nową klasę form-container */}
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
                        <input
                            className="text-input"
                            type="password"
                            placeholder="Potwierdź hasło"
                        />
                        <button className="submit-btn" type="submit">Zarejestruj się</button> {/* Zmieniono na button zamiast Button */}
                        <div className="forgot-password">
                            {/* <a href="#">Zapomniałeś hasła?</a> */}
                            <Link to="/login">Masz już konto? Zaloguj się!</Link> {/* Dodano link do logowania */}
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default RegisterPage;
