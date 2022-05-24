package com.project.veganpleasure.domain.member.dto;

import com.project.veganpleasure.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto {
    private Long id;
    private String name;
    private String nickname;
    private String vegetarianTypes;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.vegetarianTypes = member.getVegetarianTypes();
    }
}
