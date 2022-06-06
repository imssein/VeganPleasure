package com.project.veganpleasure.domain.store.repository;

import com.project.veganpleasure.domain.store.entity.Category;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.veganpleasure.domain.common.entity.QUploadFile.*;
import static com.project.veganpleasure.domain.member.entity.QMember.*;
import static com.project.veganpleasure.domain.review.entity.QReview.*;
import static com.project.veganpleasure.domain.store.entity.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> findByDistrict(District district) {
        return queryFactory.selectFrom(store)
                .leftJoin(store.reviewList, review).fetchJoin()
                .where(store.district.eq(district))
                .fetch();
    }

    @Override
    public List<Store> findByCategoriesAndVegetarianTypes(String categories,
                                                          String vegetarianTypes,
                                                          String cond) {
        return queryFactory.selectFrom(store)
                .leftJoin(store.reviewList, review).fetchJoin()
                .leftJoin(store.uploadFile, uploadFile).fetchJoin()
                .where( allOrCond(getCategoryList(categories),
                        getVegetarianTypeList(vegetarianTypes)) )
                .orderBy( sortCond(cond) )
                .fetch();
    }

    private OrderSpecifier<?> sortCond(String cond){
        if("starRating".equals(cond)){
            return store.starRating.desc().nullsLast();
        }else if("likes".equals(cond)){
            return store.likes.desc().nullsLast();
        }

        return store.id.asc();
    }

    private BooleanExpression allOrCond(List<Category> categories, List<String> vegetarianTypes){
        if(categoriesIn(categories) == null){
            return vegetarianTypesContains(vegetarianTypes);
        }else{
            return categoriesIn(categories).or(vegetarianTypesContains(vegetarianTypes));
        }
    }

    private BooleanExpression categoriesIn(List<Category> categories){
        return categories.size() > 0 ? store.category.in(categories) : null;
    }

    private BooleanExpression vegetarianTypesContains(List<String> vegetarianTypes){
        switch (vegetarianTypes.size()){
            case 1:
                return store.vegetarianTypes.contains(vegetarianTypes.get(0));
            case 2:
                return store.vegetarianTypes.contains(vegetarianTypes.get(0))
                        .or( store.vegetarianTypes.contains(vegetarianTypes.get(1)) );
            case 3:
                return store.vegetarianTypes.contains(vegetarianTypes.get(0))
                        .or( store.vegetarianTypes.contains(vegetarianTypes.get(1) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(2)) )
                        );
            case 4:
                return store.vegetarianTypes.contains(vegetarianTypes.get(0))
                        .or( store.vegetarianTypes.contains(vegetarianTypes.get(1) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(2)) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(3)) )
                        );
            case 5:
                return store.vegetarianTypes.contains(vegetarianTypes.get(0))
                        .or( store.vegetarianTypes.contains(vegetarianTypes.get(1) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(2)) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(3)) )
                                .or( store.vegetarianTypes.contains(vegetarianTypes.get(4)) )
                        );
            default:
                return null;
        }
    }

    private List<String> getVegetarianTypeList(String vegetarianTypes) {
        if(vegetarianTypes == null){
            return new ArrayList<>();
        }

        return Arrays.stream(vegetarianTypes.split(","))
                .map(v -> v.trim())
                .collect(Collectors.toList());
    }

    private List<Category> getCategoryList(String categories) {
        if(categories == null){
            return new ArrayList<>();
        }

        return Arrays.stream(categories.split(","))
                .map(c -> Category.valueOf(c.trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Store> findAllFetch() {
        return queryFactory.selectFrom(store).distinct()
                .leftJoin(store.reviewList, review).fetchJoin()
                .fetch();
    }

    @Override
    public Store findByIdFetch(Long id) {
        return queryFactory.selectFrom(store)
                .leftJoin(store.reviewList, review).fetchJoin()
                .leftJoin(review.member, member).fetchJoin()
                .where(store.id.eq(id))
                .fetchOne();
    }

}
