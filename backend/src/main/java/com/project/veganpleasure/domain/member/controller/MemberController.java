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
}
