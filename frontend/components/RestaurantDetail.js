import React from "react";
import useSWR from "swr";
import { FaRegEdit, FaRegHeart, FaPen, FaHeart } from "react-icons/fa";
import Review from "./Review";
import RestaurantPositionMap from "./RestaurantPositionMap";
const fetcher = (url) => fetch(url).then((r) => r.json());

function RestaurantDetail({ params }) {
  const url = `http://localhost:8000/restaurants/${params}`;

  const { data: item, error } = useSWR(url, fetcher);
  if (error) return "에러발생";
  if (!item) return "로딩중..";

  return (
    <div className="mx-auto max-w-6xl my-11 grid grid-cols-3 gap-4 border">
      {/* 식당명 / 평점 / 리뷰쓰기 / 찜하기 */}
      {/* 본 사람 리뷰개수 찜개수 */}
      {/* 식당 상세정보 */}
      <div className="col-span-2 bg-rose-200">
        {item.map((item) => (
          <>
            <div className="flex justify-between">
              <div className="flex">
                <p className="text-3xl">{item.name}</p>
                <p className="text-3xl ml-4 text-lime-700">4.9</p>
              </div>
              <div className="flex">
                {/* 리뷰 */}
                <div className="px-6">
                  <FaRegEdit className="mb-2 ml-3" size="2em" />
                  <p className="text-sm">리뷰 쓰기</p>
                </div>
                {/* 찜 */}
                <div className="pr-6">
                  <FaRegHeart className="mb-2" size="2em" />
                  <p className="text-sm">찜하기</p>
                </div>
              </div>
            </div>
            <div className="flex text-gray-600">
              <FaPen />
              <p className="pl-2 pr-4 text-sm">180</p>
              <FaHeart />
              <p className="pl-2 text-sm">100</p>
            </div>
            <div className="border-y-2 my-4">
              <div className="my-3 grid-cols-3 gap-4 grid">
                <p>주소</p>
                <p className="col-span-2">{item.address}</p>
              </div>
              <div className="my-3 grid-cols-3 gap-4 grid">
                <p>채식 타입</p>
                <p className="col-span-2">{item.type}</p>
              </div>
              <div className="my-3 grid-cols-3 gap-4 grid">
                <p>업종</p>
                <p className="col-span-2">{item.restaurant_type}</p>
              </div>
              <div className="grid-cols-3 gap-4 grid">
                <p>메뉴</p>
                <p className="col-span-2">{item.menu}</p>
              </div>
            </div>
            <div>
                {/* 리뷰 */} 
                <Review params={params} />
            </div>
          </>
        ))}
      </div>
      {/* 카카오 맵 */}
      <div className="border">
          <RestaurantPositionMap params={params} />
      </div>
    </div>
  );
}

export default RestaurantDetail;
