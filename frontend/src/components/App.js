import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import "./App.css";
import Footer from "./footer/Footer";
import AppContent from "./AppContent";
import LoginPanel from "./loginPanel/LoginPanel";
import UserPanel from "./userPanel/UserPanel";
import RegisterPage from "./loginPanel/RegisterPanel"; // Importujemy RegisterPage
import Cookies from "js-cookie";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(!!Cookies.get("accessToken"));

  useEffect(() => {
    setIsLoggedIn(!!Cookies.get("accessToken"));
  }, []);

  const renderLoginPage = () => {
    return isLoggedIn ? (
      <Navigate to="/" />
    ) : (
      <LoginPanel onLogin={setIsLoggedIn} />
    );
  };

  const renderAppContent = () => {
    return isLoggedIn ? <AppContent /> : <Navigate to="/login" />;
  };

  const renderRegisterPage = () => {
    return isLoggedIn ? <Navigate to="/" /> : <RegisterPage />;
  };

  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={renderLoginPage()} />
          <Route path="/" element={renderAppContent()} />
          <Route path="/register" element={renderRegisterPage()} />
          {isLoggedIn && <Route path="/details" element={<UserPanel />} />}
        </Routes>
        <Footer className="footer" />
      </div>
    </Router>
  );
}

export default App;
