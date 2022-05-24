import React, { useEffect, useState } from "react";
import GeolocationDistrict from "./GeolocationDistrict";

function Geolocation() {
  const [latitude, setLatitude] = useState("");
  const [longitude, setLongitude] = useState("");

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        setLatitude(position.coords.latitude);
        setLongitude(position.coords.longitude);
        console.log(latitude, longitude);
      });
    }
  }, [latitude, longitude]);
  return (
    <div>
      <GeolocationDistrict latitude={latitude} longitude={longitude} />
    </div>
  );
}

export default Geolocation;
