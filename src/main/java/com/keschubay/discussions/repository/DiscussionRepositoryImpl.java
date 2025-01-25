package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Discussion;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public class DiscussionRepositoryImpl extends SimpleJpaRepository<Discussion, Long> implements DiscussionRepository{

    private final EntityManager entityManager;

    public DiscussionRepositoryImpl(EntityManager entityManager) {
        super(Discussion.class, entityManager); // Pass the entity class and entity manager to SimpleJpaRepository
        this.entityManager = entityManager;
    }

    @Override
    public List<Discussion> findAllByCategory(Category category) {
        String query = "SELECT d FROM Discussion d WHERE d.category = :category";
        return entityManager.createQuery(query, Discussion.class)
                .setParameter("category", category)
                .getResultList();
    }
}
