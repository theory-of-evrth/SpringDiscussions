package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.CommentRepository;
import com.keschubay.discussions.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Discussion> getDiscussionsByCategory(Long categoryId, Pageable pageable) {
        return null;//return discussionRepository.findByCategoryId(categoryId, pageable);
    }


    public Discussion createDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    public Discussion updateDiscussion(Discussion discussion) {
        //return discussionRepository.
        return null;
    }

    @Override
    public void deleteDiscussion(Discussion discussion) {

    }

    @Override
    public Discussion getDiscussion(int discussionId) {
        return null;
    }

    @Override
    public List<Discussion> getAllDiscussions() {
        return List.of();
    }

    // TODO
    @PostMapping("/{discussionId}/comments")
    public Comment addComment(@PathVariable Long discussionId, @RequestBody Comment comment) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new RuntimeException("Discussion not found"));
        comment.setDiscussion(discussion);
        //return commentRepository.save(comment);
        return null;
    }

    // TODO delete

    // TODO update
}
