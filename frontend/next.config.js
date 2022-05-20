/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    domains: ["www.seoul.go.kr", "*"],
  },
  reactStrictMode: true,
  env: {
    BASE_URL: process.env.BASE_URL,
  },
}

module.exports = nextConfig
