import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { request } from "../axios_helper.js";
import Cookies from "js-cookie";
import axios from "axios";
import "./ContactPanel.css"; // Importujemy plik CSS dla stylizacji

function ContactPanel() {
  let navigate = useNavigate();

  const [contact, setContactData] = useState({
    nameAndSurname: "",
    email: "",
    description: "",
  });

  const [contactList, setContactList] = useState([]);

  const [isModeratorOrAdmin, setIsModOrAdm] = useState(false);

  useEffect(() => {
    axios
      .get(`http://localhost:8090/api/userRoles`, {
        withCredentials: true, // Włączanie przekazywania ciasteczek
      })
      .then((response) => {
        setIsModOrAdm(response.data);
        if (response.data == true) {
          axios
            .get(`http://localhost:8090/api/contact`, {
              withCredentials: true, // Włączanie przekazywania ciasteczek
            })
            .then((response) => {
              setContactList(response.data);
            })
            .catch((error) => {
              alert("Wystąpił błąd podczas pobierania formularza!");
              console.log(error);
            });
        }
      })
      .catch((error) => {
        alert("Wystąpił błąd podczas pobierania formularza!");
        console.log(error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!contact.nameAndSurname || !contact.email || !contact.description) {
      alert("Wypełnij wszystkie pola!");
      return;
    }
    axios
      .post(`http://localhost:8090/api/contact`, contact, {
        withCredentials: true, // Włączanie przekazywania ciasteczek
      })
      .then((res) => {
        alert("Formularz wysłany pomyślnie!");
        navigate("/");
      })
      .catch((error) => {
        alert("Wystąpił błąd podczas wysyłania formularza!");
        console.log(error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setContactData((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <>
      <div className="main-container">
        <div className="signInForm">
          {isModeratorOrAdmin && (
            <div className="table-responsive">
              <h3>Lista zgłoszeń problemów: </h3>
              <table className="table">
                <thead>
                  <tr>
                    <th scope="col">Imie i nazwisko</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Opis problemu</th>
                  </tr>
                </thead>
                <tbody>
                  {contactList.map((contact, index) => (
                    <tr className="achievement-row" key={index}>
                      <td>{contact.nameAndSurname}</td>
                      <td>{contact.email}</td>
                      <td>{contact.description}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
          {!isModeratorOrAdmin && (
            <>
              <h1>Masz problem?</h1>
              <h5>Skontaktuj się z nami poprzez formularz na dole:</h5>
              <form className="form-container" onSubmit={handleSubmit}>
                <input
                  className="text-input"
                  name="nameAndSurname"
                  placeholder="Imie i nazwisko"
                  value={contact.nameAndSurname}
                  onChange={handleInputChange}
                  autoFocus
                />
                <input
                  className="text-input"
                  name="email"
                  placeholder="E-mail"
                  value={contact.email}
                  onChange={handleInputChange}
                  autoFocus
                />
                <input
                  className="text-input"
                  name="description"
                  placeholder="Opis problemu"
                  value={contact.description}
                  onChange={handleInputChange}
                  autoFocus
                />
                <button
                  className="submit-btn"
                  type="submit"
                  id="SendButton"
                  name="SendButton"
                >
                  Wyślij
                </button>
              </form>
            </>
          )}
        </div>
      </div>
    </>
  );
}

export default ContactPanel;
