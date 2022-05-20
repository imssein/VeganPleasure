import React from "react";
import { useRouter } from "next/router";
import Head from "next/head";
import MainLayout from "../../components/MainLayout";
import RestaurantDetail from "../../components/RestaurantDetail";
import RestaurantPositionMap from "../../components/RestaurantPositionMap";

function Restaurants() {
  const router = useRouter();
  const { params = [] } = router.query;
  console.log(params);

  return (
    <div>
      <Head>
        <title>Vegan Pleasure | 맛있는 채식 한끼</title>
      </Head>
      <MainLayout>
        {/* 이미지 */}
        <RestaurantDetail params={params} />
        
        {/* 지도 */}
        {/* <RestaurantPositionMap params={params} /> */}
      </MainLayout>
    </div>
  );
}

export default Restaurants;
