package com.project.veganpleasure.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.veganpleasure.domain.common.entity.BaseEntity;
import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.member.entity.Member;
import com.project.veganpleasure.domain.store.entity.Store;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    @Lob
    private String content;
    private Long starRating;
    @JsonBackReference // 순환참조 해결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;

    @Builder
    public Review(String content, Long starRating, Store store, Member member, UploadFile uploadFile) {
        this.content = content;
        this.starRating = starRating;
        this.store = store;
        this.member = member;
        this.uploadFile = uploadFile;
    }

    // 생성 메서드
    public static Review of(String content, Long starRating,
                            Store store, Member member, UploadFile uploadFile){
        Review review = new Review();
        review.setContent(content);
        review.setStarRating(starRating);
        review.setStore(store);
        review.setMember(member);
        review.setUploadFile(uploadFile);

        store.getReviewList().add(review);

        return review;
    }
}
