import React from "react";
import "./appContent.css"; 

import AuthContent from "./AuthContent";
import BasicMap from "./Leaflet/basicMap";
import GameSelector from "./content/Content";

export default class AppContent extends React.Component {
  render() {
    return (
      <div className="AppContent"> 
        <div className="map-and-selector-container"> 
          <div className="map-container"> 
            <BasicMap />
          </div>
          <div className="selector-container"> 
            <GameSelector />
          </div>
        </div>
      </div>
    );
  }
}
