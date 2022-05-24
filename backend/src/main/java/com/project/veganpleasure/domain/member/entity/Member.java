package com.project.veganpleasure.domain.member.entity;

import com.project.veganpleasure.domain.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String role;
    private String vegetarianTypes;

    @Builder
    public Member(String email, String password, String name, String nickname, String role, String vegetarianTypes) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.vegetarianTypes = vegetarianTypes;
    }

    public List<String> getVegetarianTypeList(){
        if(this.vegetarianTypes.length() > 0){
            return Arrays.asList(this.vegetarianTypes.split(","))
                    .stream()
                    .map(s -> s.trim()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
