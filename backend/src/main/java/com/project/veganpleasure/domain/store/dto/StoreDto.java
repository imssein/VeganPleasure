package com.project.veganpleasure.domain.store.dto;

import com.project.veganpleasure.domain.store.entity.Category;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreDto {
    private Long id;
    private String name;
    private Category category;
    private String vegetarianTypes;
    private District district;
    private String address;
    private int likes;
    private int reviewCount;
    private Long starRating;

    @Builder
    public StoreDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.category = store.getCategory();
        this.vegetarianTypes = store.getVegetarianTypes();
        this.district = store.getDistrict();
        this.address = store.getAddress();
        this.likes = store.getLikes();
        this.reviewCount = store.getReviewList().size();
        this.starRating = store.getStarRating();
    }
}
