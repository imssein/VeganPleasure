import React, { useEffect, useState } from "react";
import axios from "axios";
import { BsFillGeoAltFill } from "react-icons/bs";

function GeolocationPrint({latitude, longitude}) { 
    const [address, setAddress] = useState("");
    useEffect(() => {
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
              setAddress(result.data.documents[0].address_name);
            });
        } else {
        }
      });
    return (
        <div className="w-full h-32 "> 
        <div className="font-bold text-2xl border-b-2 py-4">
            내 주변 맛집
        </div>
        <div className="flex my-4 justify-end">
            <BsFillGeoAltFill size="20" className="mr-2"/>
            <p className="font-medium">내 위치 : {address}</p>

        </div>
            
        </div>
    );
}

export default GeolocationPrint;