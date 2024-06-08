import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import "./App.css";
import Footer from "./footer/Footer";
import Header from "./header/Header";
import AppContent from "./AppContent";
import LoginPanel from "./loginPanel/LoginPanel";
import UserPanel from "./userPanel/UserPanel";
import ContactPanel from "./contactPanel/ContactPanel";
import FaqPanel from "./faqPanel/FaqPanel";
import AchievementsPanel from "./achievementsPanel/AchievementsPanel";
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

  const renderDetailsPage = () => {
    return isLoggedIn ? (
      <UserPanel onLogin={setIsLoggedIn} />
    ) : (
      <Navigate to="/" />
    );
  };

  const renderAchievementsPage = () => {
    return isLoggedIn ? <AchievementsPanel /> : <Navigate to="/" />;
  };

  const renderContactPage = () => {
    return isLoggedIn ? <ContactPanel /> : <Navigate to="/" />;
  };

  const renderFaqPage = () => {
    return isLoggedIn ? <FaqPanel /> : <Navigate to="/" />;
  };

  return (
    <Router>
      <div className="App">
        {isLoggedIn && <Header className="header" />}
        <Routes>
          <Route path="/login" element={renderLoginPage()} />
          <Route path="/" element={renderAppContent()} />
          <Route path="/register" element={renderRegisterPage()} />
          <Route path="/details" element={renderDetailsPage()} />
          <Route path="/achievements" element={renderAchievementsPage()} />
          <Route path="/contact" element={renderContactPage()} />
          <Route path="/faq" element={renderFaqPage} />
        </Routes>
        <Footer className="footer" />
      </div>
    </Router>
  );
}

export default App;
