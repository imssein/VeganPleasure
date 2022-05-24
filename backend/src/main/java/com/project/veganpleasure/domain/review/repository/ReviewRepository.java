package com.project.veganpleasure.domain.review.repository;

import com.project.veganpleasure.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
