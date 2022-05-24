import React from 'react';
import Description from './Description';
import GeolocationMap from './GeolocationMap';
// import SearchForm from './SearchForm';
import SeoulMap from './SeoulMap';

function Intro(props) {
   

    return (
        <div className='text-center my-10'>
            {/* 검색창 */}
            {/* 사이트 설명 */}
            <Description />
            {/* 서울시 자치구 선택 지도 */}
            <SeoulMap /> 
            {/* <List /> */}
        </div>
    );
}

export default Intro;