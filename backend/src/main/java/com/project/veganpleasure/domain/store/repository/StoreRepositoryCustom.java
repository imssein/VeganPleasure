package com.project.veganpleasure.domain.store.repository;

import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> findAllByDistrict(District district);
    List<Store> findAllFetch();
    Store findByIdFetch(Long id);
}
