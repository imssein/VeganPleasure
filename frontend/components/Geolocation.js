import React, { useEffect, useState } from "react";
import Axios from "axios";
import axios from "axios";
import AddrList from "./AddrList";
function Geolocation(props) {
    const [address, setAddress] = useState([]);

  useEffect(() => {
    if (navigator.geolocation) {
      //사용자 위치를 geolocation.getCurrentPosition을 이용하여
      // latitude와 longitude를 받아온다.
      navigator.geolocation.getCurrentPosition((position) => {
        let latitude = position.coords.latitude;
        let longitude = position.coords.longitude;
        //   console.log(latitude, longitude)

        if (longitude && latitude) {
          axios
            .get(
              `https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=${longitude}&y=${latitude}`,
              {
                headers: {
                  Authorization: `KakaoAK ${process.env.NEXT_PUBLIC_REST_API_KEY}`,
                },
              }
            )
            .then((result) => {
                setAddress(result.data.documents[0]);
            });
        }
      });
    }
  }, []);

  return(
      <>
      <AddrList address={address} />
      </>
  )
}

export default Geolocation;
