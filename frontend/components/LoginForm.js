import Link from "next/link";
import React, { useState, useCallback } from "react";
import { FaRegUser } from "react-icons/fa";
import Logo from "./Logo";

function LoginForm(props) {
  return (
    <div className="max-w-5xl mx-auto text-center my-32">
      {/* 로고 */}
      <Logo />
      {/* 로그인폼 */}
      <form className="my-20 ">
        <div>
          <input
            type="text"
            name="user-id"
            placeholder="아이디"
            className="mb-4 rounded-lg border-2 border-slate-200 w-80 h-11 px-7"
            required
          />
        </div>
        <div>
          <input
            name="user-password"
            type="password"
            placeholder="비밀번호"
            className="mb-11 rounded-lg border-2 border-slate-200 w-80 h-11 px-7"
            required
          />
        </div>
        <div>
          <button
            className="mb-2 bg-lime-700 w-80 h-11 rounded-lg text-white font-bold text-lg"
            type="submit"
          >
            로그인
          </button>
        </div>
        <div>
          <Link href="/signup">
            <button className="border-2 border-bg-slate-600 w-80 h-11 rounded-lg font-bold text-lg">
              회원가입
            </button>
          </Link>
        </div>
      </form>
    </div>
  );
}

export default LoginForm;
