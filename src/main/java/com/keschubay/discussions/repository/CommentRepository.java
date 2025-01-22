package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Category, Long> {
}
