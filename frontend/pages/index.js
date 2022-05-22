import Head from 'next/head'
import Script from 'next/script'
import Geolocation from '../components/Geolocation'

import Intro from '../components/Intro'
import MainLayout from '../components/MainLayout'



export default function Home() {
  return (
    <div>
      <Head>
        <title>Vegan Pleasure | 맛있는 채식 한끼</title>
      </Head>
      {/* <Script src="//dapi.kakao.com/v2/maps/sdk.js?appkey={process.env.NEXT_PUBLI.KAKAOMAP_KEY}"  /> */}
      <MainLayout>
        <Intro />
        <Geolocation />
      </MainLayout>
    </div>
  )
}
