package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findAllByCategory(Category category);
}
