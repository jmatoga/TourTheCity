import React from "react";
import "./appContent.css"; 

import AuthContent from "./AuthContent";
import BasicMap from "./Leaflet/basicMap";
import GameSelector from "./content/Content";

export default class AppContent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedMap: ""
    };
    this.handleMapChange = this.handleMapChange.bind(this);
  }
  
  handleMapChange(selectedMap) {
    this.setState({ selectedMap });
  }

  render() {
    return (
      <div className="AppContent"> 
        <div className="map-and-selector-container"> 
          <div className="map-container"> 
            <BasicMap selectedMap={this.state.selectedMap}/>
          </div>
          <div className="selector-container"> 
            <GameSelector onMapChange={this.handleMapChange}/>
          </div>
        </div>
      </div>
    );
  }
}
