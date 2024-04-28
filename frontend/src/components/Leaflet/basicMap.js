import React, { useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { useMapEvents } from "react-leaflet";
import osm from "./osm-providers";
import { Circle } from 'react-leaflet';
import L, { circleMarker } from 'leaflet';

import './basicMap.css';
import 'leaflet/dist/leaflet.css'

const greenOptions = { color: 'green' };
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
    iconUrl: require('leaflet/dist/images/marker-icon.png'),
    shadowUrl: require('leaflet/dist/images/marker-shadow.png')
});

function LocationMarker() {
  const [position, setPosition] = useState(null);
  
  const map = useMapEvents({
    click() {
      map.locate();
    },
    locationfound(e) {
      setPosition(e.latlng);
      map.flyTo(e.latlng, map.getZoom());
    },
  })

  return position === null ? null : (

    <Circle
      center={position}
      pathOptions={greenOptions}
      radius={100}
    >
      <Popup>Twoja lokalizacja</Popup>
    </Circle>
  )
}

const BasicMap = () => {
  const ZOOM_LEVEL = 15;
  const [center, setCenter] = useState({ lat: 50.05931, lng: 19.94251 });

  return (
    <MapContainer center={center} zoom={ZOOM_LEVEL} scrollWheelZoom={false} className="rounded-map">
      <TileLayer
        url={osm.maptiler.url} 
        attribution={osm.maptiler.attribution}
      />
      
      <LocationMarker/>
    </MapContainer>
  );
};

export default BasicMap;