import React, {useState} from "react";
import Link from 'next/link'

const menu = [
    {
        id: 1,
        title: '내주변',
        path: '/nearbySearch'
    },
    {
        id: 2,
        title: '채식피드',
        path: '/feed'
    },
    {
        id: 3,
        title: '채식레시피',
        path: '/recipe'
    },

]
function Navbar() {

  return (
    <div className="border-b-4 border-slate-800 flex justify-between py-2 px-2">
        {/* 로고 */}
          <Link href="/">
            <a className="mx-2 text-lime-700 md:text-xl font-mono font-extrabold">
                Vegan<br/>Pleasure
            </a>
          </Link>
          {/* 메뉴 */}
          <div className="hidden md:flex ">
          <div className="md:flex">
              {menu.map((item) => (
                  <div key={item.id} className="m-auto text-lg mx-10">
                  <Link href={item.path}><a>{item.title}</a></Link>
                  </div>
              ))}
          </div>
          </div>
          {/* 로그인 */}
          <div className="border-lime-700 m-auto text-lg mx-5 rounded-full border-2 px-4 py-2">
             <Link href="/login"><a>로그인</a></Link>
          </div>
          {/* 반응형 햄버거 */}
          
      </div>
  );
}

export default Navbar;
