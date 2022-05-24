package com.project.veganpleasure.domain.store.repository;

import com.project.veganpleasure.domain.member.entity.QMember;
import com.project.veganpleasure.domain.review.entity.QReview;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.QStore;
import com.project.veganpleasure.domain.store.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.veganpleasure.domain.member.entity.QMember.*;
import static com.project.veganpleasure.domain.review.entity.QReview.*;
import static com.project.veganpleasure.domain.store.entity.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> findAllByDistrict(District district) {
        return queryFactory.selectFrom(store)
                .leftJoin(store.reviewList, review).fetchJoin()
                .where(store.district.eq(district))
                .fetch();
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
