package com.project.veganpleasure.domain.store.repository;

import com.project.veganpleasure.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    Store findByName(String name);
}
