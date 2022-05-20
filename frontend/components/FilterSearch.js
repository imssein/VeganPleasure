import React from 'react';
const vegetarianType = [
    {
        id:1,
        type: '비건',
    },
    {
        id:2,
        type: '락토',
    },
    {
        id:3,
        type: '오보',
    },
    {
        id:4,
        type: '락토오보',
    },
    {
        id:5,
        type: '페스코',
    }
]
const category = [
    {
        id:1,
        sector: '한식'
    },
    {
        id:2,
        sector: '양식'
    },
    
    {
        id:3,
        sector: '중식'
    },
    
    {
        id:4,
        sector: '일식'
    },
    {
        id:5,
        sector: '동남아'
    },
    {
        id:6,
        sector: '카페'
    },
    {
        id:7,
        sector: '베이커리'
    } 
]

function FilterSearch(props) {
    return (
        <div>
            {/* 평점순 */}
            <div className='text-lg pr-4 border-r-2 font-medium px-2 py-2'>정렬</div>
            <div className='flex border-b-2 justify-center'>
                <div className='rounded-lg text-lg font-medium border px-2 py-2 mx-4'>평점순</div>
                <div className='rounded-lg text-lg font-medium border px-2 py-2'>인기순</div>
            </div>
             {/* 채식타입 */}
            <div className='text-lg pr-4 border-r-2 font-medium px-2 py-5'>채식 타입</div>
                <div className='grid grid-rows-3 grid-flow-col gap-4 border-b-2'>
                    {vegetarianType.map((item) => (
                    <div key={item.id} className='rounded-lg text-lg font-medium border px-2 py-2'>{item.type}</div>
                    ))}
                </div>

            {/* 업종별 */}
            <div className='text-lg pr-4 border-r-2 font-medium px-2 py-5'>카테고리</div>
                <div className='grid grid-rows-3 grid-flow-col gap-4 border-b-2'>
                    {category.map((item) => (
                       <div key={item.id} className='rounded-lg text-lg font-medium border px-2 py-2'>{item.sector}</div>

                    ))}

                </div>
            {/* 지역구 */}
        </div>
    );
}

export default FilterSearch;