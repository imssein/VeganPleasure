package com.project.veganpleasure.domain.member.service;

import com.project.veganpleasure.domain.member.dto.MemberDto;
import com.project.veganpleasure.domain.member.entity.Member;
import com.project.veganpleasure.domain.member.exception.MemberDuplicateException;
import com.project.veganpleasure.domain.member.exception.PasswordMismatchException;
import com.project.veganpleasure.domain.member.repository.MemberRepository;
import com.project.veganpleasure.domain.member.request.MemberJoinRequest;
import com.project.veganpleasure.domain.member.request.MemberLoginRequest;
import com.project.veganpleasure.domain.member.response.MemberJoinResponse;
import com.project.veganpleasure.domain.member.response.MemberLoginResponse;
import com.project.veganpleasure.global.jwt.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest){
        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail());

        checkPassword(memberLoginRequest.getPassword(), member.getPassword());

        String accessToken = jwtProvider.generateAccessToken(member);

        return new MemberLoginResponse(accessToken);
    }

    public MemberJoinResponse join(MemberJoinRequest memberJoinRequest){
        Member member = Member.builder()
                .email(memberJoinRequest.getEmail())
                .password(memberJoinRequest.getPassword())
                .name(memberJoinRequest.getName())
                .nickname(memberJoinRequest.getNickname())
                .role(memberJoinRequest.getRole())
                .vegetarianTypes(memberJoinRequest.getVegetarianTypes())
                .build();

        isValidateDuplicateMember(member);

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        Member saveMember = memberRepository.save(member);
        MemberDto memberDto = new MemberDto(saveMember);

        return new MemberJoinResponse(memberDto);
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
