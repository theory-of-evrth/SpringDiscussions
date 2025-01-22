package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    //Page<Discussion> findByCategoryId(Long categoryId, Pageable pageable);
}
