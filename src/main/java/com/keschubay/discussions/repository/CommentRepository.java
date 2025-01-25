package com.keschubay.discussions.repository;

import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDiscussion(Discussion discussion);
}
