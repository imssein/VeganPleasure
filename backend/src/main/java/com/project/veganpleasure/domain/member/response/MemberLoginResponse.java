package com.project.veganpleasure.domain.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberLoginResponse {
    private String accessToken;
}
