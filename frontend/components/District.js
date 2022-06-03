import React from "react";
import useSWR from "swr";
import Link from "next/link";
const fetcher = (url) => fetch(url).then((r) => r.json());

function District({ params }) {
  const url = `http://localhost:9090/v1/api/stores/${params}`;

  const { data: item, error } = useSWR(url, fetcher);
  // if (error) return "에러발생";
  if (!item) return "로딩중..";

  return (
    <div className="text-center mx-auto max-w-4xl">
      {/* 클릭한 자치구명 */}
      <div className="flex justify-center">
        <div className="text-2xl font-extrabold text-lime-700 mb-24">
          {item[0].district}
        </div>
        <div className="text-2xl font-extrabold text-lime-700 mb-24 ml-3">
          ({item.length}개)
        </div>
      </div>

      {/* 식당리스트 */}
      <div className="border">
        {item.map((item) => (
          <div key={item.id}>
            <Link href={`/restaurants/${item.id}`}>
              <div className="border-b-2 pt-2 pb-9 my-4 mx-4">
                <p className="text-xl">{item.name}</p>
                <p>{item.category}</p>
                <p className="text-lime-700">{item.vegetarianTypes}</p>
                {/* <p>{item.address}</p> */}
                <p>
                  찜 {item.likes} | 리뷰수 {item.reviewCount} | 평점{" "}
                  {item.starRating}{" "}
                </p>
              </div>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}

export default District;
