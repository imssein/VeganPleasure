import React, {useEffect} from 'react';
import Axios from 'axios';
import axios from 'axios';

function Geolocation(props) {
    
    useEffect(() => {
        if (navigator.geolocation) {
            //사용자 위치를 geolocation.getCurrentPosition을 이용하여
            // latitude와 longitude를 받아온다.
            navigator.geolocation.getCurrentPosition((position) => {
              let latitude = position.coords.latitude;
              let longitude = position.coords.longitude;
            //   console.log(latitude, longitude)
             

            if( longitude && latitude ){
                axios.get(`https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=${longitude}&y=${latitude}`, {
                    headers: { Authorization: `KakaoAK ${process.env.NEXT_PUBLIC_REST_API_KEY}` }
                }).then(result => {
                    // 서울특별시 __ 구 __동 
                    const addr_name = result.data.documents[0].address_name;
                    // __구
                    const region_name = result.data.documents[0].region_2depth_name;
                    console.log(addr_name);
                    console.log(region_name);
                }) 
            }
            });
        }
    })
    return (
        <div>
            
        </div>
    );
}

export default Geolocation;