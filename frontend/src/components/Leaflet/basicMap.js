import React, { useState, useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup, Circle} from "react-leaflet";
import { useMapEvents } from "react-leaflet";
import L from 'leaflet';

import { Icon } from 'leaflet';

import osm from "./osm-providers";
import './basicMap.css';
import 'leaflet/dist/leaflet.css';

const customIcon = new Icon({
  iconUrl: process.env.PUBLIC_URL + '/mapMarkersImages/marker.png',
  iconSize: [40, 40],
  iconAnchor: [12, 41],
  popupAnchor: [8, -34],
});

//Zestawy współrzędnych z opisami i zdjęciami
const monumentsCoordinates = [
  {
    position: [50.05346,19.93596],
    name: "Zamek Królewski na Wawelu",
    description: "Symbol potęgi i władzy polskich królów, to zamek pełen historii i niezwykłych dzieł sztuki.",
    imageUrl: "/monuments/wawel.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Wawel"
  },
  {
    position: [50.06169,19.93735],
    name: "Sukiennice",
    description: "Centrum handlowe i kulturalne Krakowa od setek lat, Sukiennice to ikona miasta.",
    imageUrl: "/monuments/sukiennice.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Sukiennice_w_Krakowie"
  },
  {
    position: [50.06166,19.93881],
    name: "Kościół Mariacki",
    description: "Jeden z najważniejszych kościołów w Polsce, z niezwykłym ołtarzem Wita Stwosza.",
    imageUrl: "/monuments/kosciolMariacki.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Sukiennice_w_Krakowie"
  },
  {
    position: [50.06520,19.94150],
    name: "Barbakan",
    description: "Impresyjna średniowieczna fortyfikacja, która kiedyś broniła miasta, obecnie stanowi malowniczy element krajobrazu Krakowa.",
    imageUrl: "/monuments/barbakan.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Barbakan"
  },
  {
    position: [50.06165,19.93341],
    name: "Collegium Maius",
    description: "Najstarszy budynek Uniwersytetu Jagiellońskiego powstały z zakupionej przez króla Władysława Jagiełło w 1400 r. kamienicy. Znajdują się tutaj zabytkowe kolekcje: naukowa, malarska, rzeźby różnych epok, rzemiosła artystycznego.",
    imageUrl: "/monuments/collegiumMaius.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Collegium_Maius_Uniwersytetu_Jagiello%C5%84skiego"
  }
];

const universityCoordinates = [
  { position: [50.06303,19.92211], 
    name: "Uniwersytet Rolniczy", 
    description: "Oto i największa ale i niedoceniana uczelnia w Krakowie.", 
    imageUrl: "/university/rolniczyGlowny.jpg",
    moreInfoLink: "https://urk.edu.pl/"
  },
  { position: [50.07286,20.00028], 
    name: "Akademia Wychowania Fizycznego", 
    description: "Na zdjęciu został ukazany na tyle urodziwy budynek, że kręcono w nim film", 
    imageUrl: "/university/awfBudynekGlowny.jpg",
    moreInfoLink: "https://www.awf.krakow.pl/" 
  },
  { position: [50.07099,19.94305], 
    name: "Politechnika Krakowska", 
    description: "Największa politechnika w Krakowie i jej najwybitniejszy wydział, na którym aktualnie się znajmujemy-WIEiK.", 
    imageUrl: "/university/polibudaWieik.jpg",
    moreInfoLink: "https://wieik.pk.edu.pl/"
  },
  { position: [50.06453,19.92361], 
    name: "Akademia Górniczo-Hutnicza", 
    description: "Arcywróg politechniki, ale właśnie tutaj chcielibyśmy iść na studia magisterskie.", 
    imageUrl: "/university/aghGlowny.jpg",
    moreInfoLink: "https://www.agh.edu.pl/"
  },
  { position: [50.05735,19.93910], 
    name: "Uniwersytet Jagielloński", 
    description: "UJ jaki jest każdy widzi. Tutaj konkretnie widać Collegium Broscianum, m.in. z wydziałem filozofii w swych murach.", 
    imageUrl: "/university/ujFilozofia.jpg",
    moreInfoLink: "https://www.uj.edu.pl/"
  }
];
const moundsCoordinates = [
  { position: [50.05491,19.89335], 
    name: "Kopiec Kościuszki", 
    description: "Wzniesiony na Wzgórzu Kościuszki, upamiętnia bohaterstwo Tadeusza Kościuszki i jego udział w walkach o niepodległość Polski.", 
    imageUrl: "/mounds/kopiecKosciuszki.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Kopiec_Ko%C5%9Bciuszki_w_Krakowie" 
  },
  { position: [50.06004,19.84716], 
    name: "Kopiec Piłsudskiego", 
    description: "Położony na Skałce, symbolizuje pamięć i czyny Józefa Piłsudskiego, ważnej postaci w historii Polski.", 
    imageUrl: "/mounds/kopiecPilsudskiego.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Kopiec_J%C3%B3zefa_Pi%C5%82sudskiego_w_Krakowie" 
  },
  { position: [50.03808,19.95844], 
    name: "Kopiec Krakusa", 
    description: "Usytuowany na wzgórzu Lasoty, według legendy to mogiła mitycznego Kraka, założyciela Krakowa.", 
    imageUrl: "/mounds/kopiecKrakusa.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Kopiec_Krakusa"
  },
  { position: [50.07022,20.06807], 
    name: "Kopiec Wandy", 
    description: "Znajduje się w Podgórzu, opowiada legendę o księżniczce Wandzie, która rzekomo popełniła samobójstwo, aby uchronić Kraków przed najeźdźcami.", 
    imageUrl: "/mounds/kopiecWandy.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Kopiec_Wandy"
  },
  { position: [50.04425,19.91724], 
    name: "Kopiec Jana Pawła II", 
    description: "Poświęcony jest pamięci i dziedzictwu świętego papieża Jana Pawła II, urodzonego w Wadowicach niedaleko Krakowa. Jest najmłodszym krakowskim kopcem.", 
    imageUrl: "/mounds/kopiecJanaPawla2.jpg",
    moreInfoLink: "https://pl.wikipedia.org/wiki/Kopiec_Jana_Paw%C5%82a_II_w_Krakowie"
  }
];

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
  const [coordinates, setCoordinates] = useState(universityCoordinates);

  useEffect(() => {
    switch(selectedMap) {
      case "monumentsTour":
        setCoordinates(monumentsCoordinates);
        break;
      case "universityTour":
        setCoordinates(universityCoordinates);
        break;
      case "moundTour":
        setCoordinates(moundsCoordinates);
        break;
      default:
        setCoordinates(universityCoordinates);
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
