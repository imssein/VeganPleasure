package com.project.veganpleasure.domain.store.dto;

import com.project.veganpleasure.domain.review.dto.ReviewDto;
import com.project.veganpleasure.domain.store.entity.Category;
import com.project.veganpleasure.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Id @GeneratedValue
 *     @Column(name = "store_id")
 *     private Long id;
 *     private String name;
 *     @Enumerated(EnumType.STRING)
 *     private Category category;
 *     private String address;
 *     @Enumerated(EnumType.STRING)
 *     private District district;
 *     private String phoneNumber;
 *     private int likes;
 *     private Long starRating;
 *     private String menu;
 *     @OneToMany(mappedBy = "store")
 *     private List<Review> reviewList = new ArrayList<>();
 *     @OneToOne(fetch = FetchType.LAZY)
 *     @JoinColumn(name = "uploadfile_id")
 *     private UploadFile uploadFile;
 *     private String vegetarianTypes;
 */
@NoArgsConstructor
@Getter
@Setter
public class StoreDetailDto {
    private Long id;
    private String name;
    private Category category;
    private String vegetarianTypes;
    private String address;
    private String menu;
    private String phoneNumber;
    private int likes;
    private Long starRating;
    private List<ReviewDto> reviewList = new ArrayList<>();

    @Builder
    public StoreDetailDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.category = store.getCategory();
        this.vegetarianTypes = store.getVegetarianTypes();
        this.address = store.getAddress();
        this.menu = store.getMenu();
        this.phoneNumber = store.getPhoneNumber();
        this.address = store.getAddress();
        this.starRating = store.getStarRating();
        this.reviewList = store.getReviewList()
                .stream()
                .map(r -> new ReviewDto(r))
                .collect(Collectors.toList());
    }
}
