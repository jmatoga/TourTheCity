import React from "react";
import "./appContent.css";

import logo from "../components/logo.svg";
import Header from "./header/Header";
import AuthContent from "./AuthContent";
import BasicMap from "./Leaflet/basicMap";
import GameSelector from "./content/Content";

export default class AppContent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedMap: "",
    };
    this.handleMapChange = this.handleMapChange.bind(this);
  }

  handleMapChange(selectedMap) {
    this.setState({ selectedMap });
  }

  //"container-fluid" - klasa z Bootstrapa (responsywność i szerokość na 100%)
  //"row" - wiersz w układzie Bootstrap zawierający kolumny
  //"col-12" - 12 kolumn na 12 możliwych
  render() {
    return (
      <div className="AppContent">
        <div className="container-fluid">
          <div className="map-and-selector-container">
            <div className="map-container">
              <BasicMap selectedMap={this.state.selectedMap} />
            </div>
            <div className="selector-container">
              <GameSelector onMapChange={this.handleMapChange} />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
