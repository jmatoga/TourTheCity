import Nav from "react-bootstrap/Nav"; //npm install react-bootstrap bootstrap
import Navbar from "react-bootstrap/Navbar";
import "./header.css";

function MainNavigationWithAlert() {
  return (
    <Navbar id="main-navigation-bar">
      {/* logo po kliknięciu którego /home */}
      <Navbar.Brand href="/">Gra Terenowa</Navbar.Brand>

      <Nav
        // className="main-navigation-bar"
        activeKey="/" //klucz elementu aktualnie (pierwotnie) aktywnego
        //onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
      >
        <Nav.Item>
          <Nav.Link
            //eventKey to unikalny klucz identyfikujący element nawigacyjny
            //może być do identyfikacji klikniętego elementu
            eventKey="/history"
            href="/history"
          >
            Historia
          </Nav.Link>
        </Nav.Item>

        <Nav.Item>
          <Nav.Link eventKey="/achievements" href="/achievements">
            Osiągnięcia
          </Nav.Link>
        </Nav.Item>

        <Nav.Item>
          <Nav.Link eventKey="/faq" href="/faq">
            FAQ
          </Nav.Link>
        </Nav.Item>

        <Nav.Item>
          <Nav.Link eventKey="/contact" href="/contact">
            Kontakt
          </Nav.Link>
        </Nav.Item>
      </Nav>

      <Navbar.Toggle />

      <Navbar.Collapse
        //zwija się na mneijszych ekranach
        className="justify-content-end"
      >
        <Nav.Link id="account-element-right" href="/details">
          Konto
        </Nav.Link>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default MainNavigationWithAlert;
