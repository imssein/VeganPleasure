package com.project.veganpleasure.domain.post.repository;

import com.project.veganpleasure.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
