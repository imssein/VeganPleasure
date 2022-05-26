import { useEffect, useState } from "react";
import useSWR from "swr";

const fetcher = (...args) => fetch(...args).then((res) => res.json());

function RestaurantPositionMap({ params }) {
  const url = `http://localhost:9090/v1/api/stores/detail/${params}`;
//   console.log(`${params}`)
  const { data, error } = useSWR(url, fetcher);

  const [mapLoaded, setMapLoaded] = useState(false);

  const key = process.env.NEXT_PUBLIC_KAKAOMAP_KEY;

  useEffect(() => {
    const $script = document.createElement("script");
    $script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${key}&autoload=false&libraries=services`;
    console.log(process.env.NEXT_PUBLIC_KAKAOMAP_KEY);
    $script.addEventListener("load", () => setMapLoaded(true));
    document.head.appendChild($script);
  }, []);

  useEffect(() => {
    if (!mapLoaded) return;
    kakao.maps.load(() => {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3, // 지도의 확대 레벨
      };
      // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다.
      var map = new kakao.maps.Map(container, options);

      // 주소-좌표 변환 객체를 생성한다.
      var geocoder = new kakao.maps.services.Geocoder();
      {data && data.map((data) => {
          geocoder.addressSearch(`${data.address}`, (result, status) => {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });
              // console.log(`${data.id}`);
              // console.log(`${data.address}`)

              map.setCenter(coords);
            }
            //마커에 마우스오버 이벤트를 등록 
          kakao.maps.event.addListener(marker, 'mouseover', () => {
            // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          infowindow.open(map, marker);
        })
        kakao.maps.event.addListener(marker, 'mouseout', () => {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
         infowindow.close();
        })

        kakao.maps.event.addListener(marker, 'click', () => {
          console.log(`${data.id}`); 
          
         })
          });
        });
      }
    });
  }, [mapLoaded]);

  return (
    <div>
      <div id="map" style={{ width: "370px", height: "370px" }}></div>
    </div>
  );
}

export default RestaurantPositionMap;
