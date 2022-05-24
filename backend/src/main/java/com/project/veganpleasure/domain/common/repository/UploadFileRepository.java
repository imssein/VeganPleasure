package com.project.veganpleasure.domain.common.repository;

import com.project.veganpleasure.domain.common.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
