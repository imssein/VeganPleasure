package com.project.veganpleasure.domain.review.dto;

import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.member.dto.MemberDto;
import com.project.veganpleasure.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private Long id;
    private String content;
    private Long starRating;
    private MemberDto member;
    private UploadFile uploadFile;

    @Builder
    public ReviewDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.starRating = review.getStarRating();
        this.member = new MemberDto(review.getMember());
        this.uploadFile = review.getUploadFile();
    }
}
