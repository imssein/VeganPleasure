package com.project.veganpleasure.domain.member.response;

import com.project.veganpleasure.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberJoinResponse {
    private MemberDto memberDto;
}
