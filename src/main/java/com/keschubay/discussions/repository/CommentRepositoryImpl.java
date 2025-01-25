package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public class CommentRepositoryImpl extends SimpleJpaRepository<Comment, Long> implements CommentRepository{

    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(Comment.class, entityManager); // Pass the entity class and entity manager to SimpleJpaRepository
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> findAllByDiscussion(Discussion discussion) {
        String query = "SELECT c FROM Comment c WHERE c.discussion = :discussion";
        return entityManager.createQuery(query, Comment.class)
                .setParameter("discussion", discussion)
                .getResultList();
    }
}
