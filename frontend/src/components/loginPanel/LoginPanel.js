import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './loginPanel.css'; // Importujemy plik CSS dla stylizacji

function LoginPanel({ onLogin }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = (event) => {
        event.preventDefault();
        // Prosta walidacja
        if (email === 'admin@gmail.com' && password === 'admin') {
            onLogin(true);
        } else {
            setError('Niepoprawny email lub hasło');
        }
    };

    return (
        <>
            <div className="main-container"> {/* Zastosowaliśmy nową klasę main-container */}
                {/* <div className="header">
                        <p>Gra Terenowa</p>
                    </div> */}
                    <div className="signInForm">
                <h3>Witaj w Grze Terenowej!</h3> {/* Usunięto komponent Typography */}
                <h5>Zaloguj się, aby kontynuować</h5>
                <form className="form-container" onSubmit={handleLogin}> {/* Zastosowaliśmy nową klasę form-container */}
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
                    {error && <p className="error-message">{error}</p>}
                    <button className="submit-btn" type="submit">Sign In</button> {/* Zmieniono na button zamiast Button */}
                    <div className="forgot-password">
                        <a href="#">Zapomniałeś hasła?</a>
                    </div>
                    <div className="footer">
                        <p>Nie masz konta?
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
