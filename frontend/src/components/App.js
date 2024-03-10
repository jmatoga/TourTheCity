import "./App.css";
import logo from "../logo.svg";
import Header from "./Header";
import AppContent from "./AppContent";

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
    </div>
  );
}

export default App;
