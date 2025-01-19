package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
