package com.project.veganpleasure.domain.member.response;

import com.project.veganpleasure.global.jwt.dto.AccessToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberLoginResponse {
    private AccessToken accessToken;
    private String refreshToken;
}
