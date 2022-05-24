package com.project.veganpleasure.domain.post.entity;

import com.project.veganpleasure.domain.common.entity.BaseEntity;
import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploadfild_id")
    private UploadFile uploadFile;

    @Builder
    public Post(String title, String content, Member member, UploadFile uploadFile) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.uploadFile = uploadFile;
    }
}
