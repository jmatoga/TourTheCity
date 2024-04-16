import "./App.css";
import logo from "../logo.svg";
import Header from "./header/Header";
import AppContent from "./AppContent";
import Footer from "./footer/Footer"

function App() {
  return (
    <div className="App">
      <Header pageTitle="Tour The City" logoSrc={logo} />
      <div className="container-fluid">
        <div className="row">
          <div className="col-12">
            <AppContent />
          </div>
        </div>
      </div>
    <Footer/>
    </div>
  );
}

export default App;
