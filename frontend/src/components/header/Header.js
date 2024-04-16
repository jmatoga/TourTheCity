import Nav from 'react-bootstrap/Nav'; //npm install react-bootstrap bootstrap
import Navbar from 'react-bootstrap/Navbar';
import './header.css';

function MainNavigationWithAlert() {
  return (
    <Navbar 
        id="main-navigation-bar"
    >
      
        <Navbar.Brand href="/home">Gra Terenowa</Navbar.Brand>

        <Nav 
          // className="main-navigation-bar" 
          activeKey="/home"
          onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}>
            
          <Nav.Item>
            <Nav.Link eventKey="/faq">
              Historia
            </Nav.Link>
          </Nav.Item>

          <Nav.Item>
           <Nav.Link eventKey="/faq">FAQ</Nav.Link>
          </Nav.Item>

          <Nav.Item>
           <Nav.Link eventKey="/contact">Kontakt</Nav.Link>
          </Nav.Item>
       </Nav>

        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
        <Nav.Link id="account-element-right" eventKey="/account">Konto</Nav.Link>
        </Navbar.Collapse>
        
    </Navbar>
    );
  }


export default MainNavigationWithAlert;