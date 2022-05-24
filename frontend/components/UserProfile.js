import Link from 'next/link';
import React, {useCallback} from 'react';
import Logo from './Logo';
import PageName from './PageName';
import Image from 'next/image';

function UserProfile({setIsLoggedIn}) {

    return (
        <div className="max-w-5xl mx-auto my-7 text-center">
         
            <div className='border-b-2 pb-7 cursor-pointer '>
                    <Logo />
            </div>
           
            <div className='py-7'>
                <PageName name="마이페이지" />
            </div>
            {/* 사용자 이름 / 채식 타입 / 프로필 수정 버튼 */}
            <div className='py-6 text-lg font-bold border-2'>
                <Image src="/images/img.png" alt="avatar" width={120} height={120} className="rounded-full ring-2 ring-white"/>
                <p className='py-4'>세인</p>
                <div className='flex justify-center'>
                    <p className='rounded-full border-2 bg-lime-100 w-24 py-2 text-slate-700 mr-4'>비건</p>
                    <p className='rounded-full border-2 bg-lime-100 w-24 py-2 text-slate-700'>락토</p>
                </div>
                <div className='justify-center mt-6'>
                    <button className='rounded-lg border-2 w-32 py-2 text-slate-700 mr-4'>정보 수정</button>    
                    <button className='rounded-lg border-2 w-32 py-2 text-red-800 mr-4'>로그아웃</button>    
                </div>
            </div>
            {/* 팔로워 / 팔로잉  */}
            {/* 평가한 맛집 __ 개  */}
            {/* 좋아요한 맛집 __  */}
            </div>  
    );
}

export default UserProfile;