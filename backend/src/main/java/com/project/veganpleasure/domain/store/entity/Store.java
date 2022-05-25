package com.project.veganpleasure.domain.store.entity;

import com.project.veganpleasure.domain.common.entity.BaseEntity;
import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.review.entity.Review;
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
public class Store extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address;
    @Enumerated(EnumType.STRING)
    private District district;
    private String phoneNumber;
    private int likes;
    private Long starRating;
    @Lob
    private String menu;
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploadfile_id")
    private UploadFile uploadFile;
    private String vegetarianTypes;

    @Builder
    public Store(String name, Category category, String address, District district, String phoneNumber, int likes, Long starRating, String menu, List<Review> reviewList, UploadFile uploadFile, String vegetarianTypes) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.district = district;
        this.phoneNumber = phoneNumber;
        this.likes = likes;
        this.starRating = starRating;
        this.menu = menu;
        this.reviewList = reviewList;
        this.uploadFile = uploadFile;
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

    public List<String> getMenuList(){
        if(this.menu.length() > 0){
            return Arrays.asList(this.menu.split(","))
                    .stream()
                    .map(s -> s.trim()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
