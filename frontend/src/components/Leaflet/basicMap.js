import React, { useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import osm from "./osm-providers";
import './basicMap.css';
import 'leaflet/dist/leaflet.css'

const BasicMap = () => {
  const ZOOM_LEVEL = 15;
  const [center, setCenter] = useState({ lat: 50.05931, lng: 19.94251 });

  return (
    <MapContainer center={center} zoom={ZOOM_LEVEL} scrollWheelZoom={false} className="rounded-map">
      <TileLayer
        url={osm.maptiler.url} 
        attribution={osm.maptiler.attribution}
      />
      <Marker position={center}>
        <Popup>
          A pretty CSS3 popup. <br /> Easily customizable.
        </Popup>
      </Marker>
    </MapContainer>
  );
};

export default BasicMap;