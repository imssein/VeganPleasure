import React from "react";
import useSWR from "swr";
import Link from 'next/link'
const fetcher = (url) => fetch(url).then((r) => r.json());

function District({ params }) {

  const url = `http://localhost:8000/seoul/${params}`;

  const { data: item, error } = useSWR(url, fetcher);
  if (error) return "에러발생";
  if (!item) return "로딩중..";

  return (
    <div className="text-center mx-auto max-w-4xl">
        {/* 클릭한 자치구명 */}
        <div className="text-2xl font-extrabold text-lime-700 mb-24">
            {item[0].district}
        </div>
        {/* 식당리스트 */}
        <div className="border">
              {item.map((item) => (
                <div key={item.id}>
                <Link href={`/restaurants/${item.id}`}>
                <div className='border-b-2 pt-2 pb-9 my-4 mx-4'>     
                    <p className='text-xl'>{item.name}</p>
                    <p>{item.restaurant_type}</p>
                    <p className="text-lime-700">{item.type}</p>
                    {/* <p>{item.address}</p> */} 
                    <p>찜 | 리뷰수 | 평점</p>
                </div>
                </Link>
                </div>
              ))}
          </div>
    </div>
  )
}

export default District;
