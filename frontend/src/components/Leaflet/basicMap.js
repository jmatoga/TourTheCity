import React, { useState, useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup, Circle} from "react-leaflet";
import { useMapEvents } from "react-leaflet";
import L from 'leaflet';

import { monumentsCoordinates, universityCoordinates, moundsCoordinates } from './MapCoordinates';

import { Icon } from 'leaflet';

import osm from "./osm-providers";
import './basicMap.css';
import 'leaflet/dist/leaflet.css';

const savedMonumentsCoordinates = monumentsCoordinates;
const savedUniversityCoordinates = universityCoordinates;
const savedMoundsCoordinates = moundsCoordinates;


const customIcon = new Icon({
  iconUrl: process.env.PUBLIC_URL + '/mapMarkersImages/marker.png',
  iconSize: [50, 50],
  iconAnchor: [12, 41],
  popupAnchor: [8, -34],
});

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
  });

  return position === null ? null : (
    <Circle
      center={position}
      pathOptions={greenOptions}
      radius={100}
    >
      <Popup>Twoja lokalizacja</Popup>
    </Circle>
  );
}

const BasicMap = ({ selectedMap }) => {
  const ZOOM_LEVEL = 12;
  const [center, setCenter] = useState({ lat: 50.05931, lng: 19.94251 });
  const [coordinates, setCoordinates] = useState(savedUniversityCoordinates);

  useEffect(() => {
    switch(selectedMap) {
      case "monumentsTour":
        setCoordinates(savedMonumentsCoordinates);
        break;
      case "universityTour":
        setCoordinates(savedUniversityCoordinates);
        break;
      case "moundTour":
        setCoordinates(savedMoundsCoordinates);
        break;
      default:
        setCoordinates(savedUniversityCoordinates);
    }
  }, [selectedMap]);

  return (
    <MapContainer center={center} zoom={ZOOM_LEVEL} scrollWheelZoom={false} className="rounded-map">
      <TileLayer
        url={osm.maptiler.url} 
        attribution={osm.maptiler.attribution}
      />
      {coordinates.map((markerData, index) => (
        <Marker key={index} position={markerData.position} icon={customIcon}>
          <Popup>
            <div className="popupContainer">
              <h5>{markerData.name}</h5>
              <img src={process.env.PUBLIC_URL + '/mapMarkersImages' + markerData.imageUrl} alt="Zdjęcie zabytku" />
              <p>{markerData.description}</p>
              <a href={markerData.moreInfoLink}>Kliknij po więcej informacji</a>
            </div>
          </Popup>
        </Marker>
      ))}
      <LocationMarker />
    </MapContainer>
  );
};

export default BasicMap;
