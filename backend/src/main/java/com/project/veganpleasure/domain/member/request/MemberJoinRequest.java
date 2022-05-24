package com.project.veganpleasure.domain.member.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String role;
    private String vegetarianTypes;
}
