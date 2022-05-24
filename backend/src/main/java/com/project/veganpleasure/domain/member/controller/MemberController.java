package com.project.veganpleasure.domain.member.controller;

import com.project.veganpleasure.domain.member.dto.MemberDto;
import com.project.veganpleasure.domain.member.entity.Member;
import com.project.veganpleasure.domain.member.request.MemberJoinRequest;
import com.project.veganpleasure.domain.member.request.MemberLoginRequest;
import com.project.veganpleasure.domain.member.response.MemberJoinResponse;
import com.project.veganpleasure.domain.member.response.MemberLoginResponse;
import com.project.veganpleasure.domain.member.service.MemberService;
import com.project.veganpleasure.global.jwt.dto.AccessToken;
import com.project.veganpleasure.global.jwt.exception.TokenIsInvalidException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("v1/api/members")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public MemberLoginResponse login(@RequestBody MemberLoginRequest memberLoginRequest){
        return memberService.login(memberLoginRequest);
    }

    @ApiOperation("회원가입")
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberJoinResponse join(@RequestBody MemberJoinRequest memberJoinRequest){
        Member member = Member.builder()
                .email(memberJoinRequest.getEmail())
                .password(memberJoinRequest.getPassword())
                .name(memberJoinRequest.getName())
                .nickname(memberJoinRequest.getNickname())
                .role(memberJoinRequest.getRole())
                .vegetarianTypes(memberJoinRequest.getVegetarianTypes())
                .build();

        memberService.join(member);

        return new MemberJoinResponse(new MemberDto(member));
    }

    @ApiOperation("엑세스 토큰 재발급")
    @GetMapping("/accesstoken")
    @ResponseStatus(HttpStatus.CREATED)
    public AccessToken requestAccessToken(@RequestHeader String refreshToken) throws TokenIsInvalidException {
        return memberService.getAccessTokenBy(refreshToken);
    }

    /**
     * 로그인 다음과 같은 방식으로 구현
     *
     * 로그인을 하면 액세스 토큰과 만료시간( 10분 ), 리프레쉬 토큰( 만료시간 100분 )이 주어짐
     * 액세스 토큰의 만료시간이 지나면 재발급 받아야함
     * 리프레쉬 토큰의 만료시간이 지나면 다시 로그인해야함
     */
}
