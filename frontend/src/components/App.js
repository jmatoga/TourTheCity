import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Footer from './footer/Footer';
import AppContent from './AppContent';
import LoginPanel from './loginPanel/LoginPanel';
import RegisterPage from './loginPanel/RegisterPanel'; // Importujemy RegisterPage

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLogin = () => {
        setIsLoggedIn(true);
    };

    const renderLoginPage = () => {
        return isLoggedIn ? <Navigate to="/" /> : <LoginPanel onLogin={handleLogin} />;
    };

    const renderAppContent = () => {
        return isLoggedIn ? <AppContent /> : <Navigate to="/login" />;
    };

    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/login" element={renderLoginPage()} />
                    <Route path="/" element={renderAppContent()} />
                    <Route path="/register" element={<RegisterPage />} /> {/* Dodano ścieżkę rejestracji */}
                </Routes>
                <Footer className="footer" />
            </div>
        </Router>
    );
}

export default App;
