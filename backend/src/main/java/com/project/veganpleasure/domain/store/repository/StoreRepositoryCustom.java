package com.project.veganpleasure.domain.store.repository;

import com.project.veganpleasure.domain.store.entity.Category;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> findByDistrict(District district);
    List<Store> findByCategoriesAndVegetarianTypes(String categories, String vegetarianTypes, String cond);
    List<Store> findAllFetch();
    Store findByIdFetch(Long id);
}
