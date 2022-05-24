import React from "react";
import Link from "next/link";
function Logo(props) {
  return (
    <Link href="/">
      <div className="text-5xl font-bold text-lime-700">VEGAN PLEASURE</div>
    </Link>
  );
}

export default Logo;