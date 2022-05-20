import React from 'react';
import FilterSearch from '../components/FilterSearch';
import GeolocationMap from '../components/GeolocationMap';
import MainLayout from '../components/MainLayout';
import PageName from '../components/PageName';

function nearbySearch(props) {
    return (
        <MainLayout>
            <div className='max-w-6xl mx-auto text-center my-11'>
                {/* 타이틀 */}
                <PageName name="내주변 맛집" />
                <div className=' grid grid-cols-3 gap-4 my-11'>
                {/* 필터 */}
                <div className='bg-rose-200'>
                    <FilterSearch />
                </div>
                {/* 지도 */}
                <div className='col-span-2'>
                    <GeolocationMap />
                </div>
                {/* 식당 리스트 */}
                </div>
            </div>
        </MainLayout>
    );
}

export default nearbySearch;