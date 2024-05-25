import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import "./App.css";
import Footer from "./footer/Footer"
import AppContent from "./AppContent";
import LoginPage from './loginPanel/LoginPanel';

function App() { 
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  const handleLogin = () => {
    // Tutaj możesz umieścić logikę autoryzacji
    // Załóżmy, że logowanie jest pomyślne
    return setIsLoggedIn(true);
  };

  const renderLoginPage = () => {
    return isLoggedIn ? <Navigate to="/" /> : <LoginPage onLogin={handleLogin} />;
  };

  const renderAppContent = () => {
    return isLoggedIn ? <AppContent /> : <Navigate to="/login" />;
  };

  return (
    <Router>
      <div className="App">
        <div className="content">
        <Routes>
            <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
          <Route path="/" element={<AppContent />} />
          </Routes> 
        </div>
        <Footer className="footer"/>
      </div>
    </Router>
  );
}

export default App;

