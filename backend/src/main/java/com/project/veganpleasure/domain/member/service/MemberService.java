package com.project.veganpleasure.domain.member.service;

import com.project.veganpleasure.domain.member.entity.Member;
import com.project.veganpleasure.domain.member.exception.MemberDuplicateException;
import com.project.veganpleasure.domain.member.exception.PasswordMismatchException;
import com.project.veganpleasure.domain.member.repository.MemberRepository;
import com.project.veganpleasure.domain.member.request.MemberLoginRequest;
import com.project.veganpleasure.domain.member.response.MemberLoginResponse;
import com.project.veganpleasure.global.jwt.dto.AccessToken;
import com.project.veganpleasure.global.jwt.exception.TokenIsInvalidException;
import com.project.veganpleasure.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest){
        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail());
        checkPassword(memberLoginRequest.getPassword(), member.getPassword());

        AccessToken accessToken = jwtService.generateAccessTokenBy(member);
        String refreshToken = jwtService.generateRefreshToken(member);

        return new MemberLoginResponse(accessToken, refreshToken);
    }

    public Member join(Member member){
        isValidateDuplicateMember(member);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public String getUsername(String accessToken){
        return jwtService.findMemberByToken(accessToken).getEmail();
    }

    public AccessToken getAccessTokenBy(String refreshToken) throws TokenIsInvalidException {
        return jwtService.generateAccessTokenBy(refreshToken);
    }

    private void isValidateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null){
            throw new MemberDuplicateException();
        }
    }

    private void checkPassword(String loginPassword, String password) {
        if( !bCryptPasswordEncoder.matches(loginPassword, password) ){
            throw new PasswordMismatchException();
        }
    }
}
