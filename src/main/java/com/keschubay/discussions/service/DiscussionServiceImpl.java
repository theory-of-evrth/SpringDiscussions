package com.keschubay.discussions.service;

import com.keschubay.discussions.CurrentUserUtil;
import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.AppUserRepository;
import com.keschubay.discussions.repository.CommentRepository;
import com.keschubay.discussions.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Discussion> getDiscussionsByCategory(Long categoryId, Pageable pageable) {
        return null;//return discussionRepository.findByCategoryId(categoryId, pageable);
    }


    public Discussion createDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    public Discussion updateDiscussion(Discussion discussion) {
        if (Objects.equals(discussion.getCreatedBy().getUsername(), CurrentUserUtil.getCurrentUsername()) )
        {
            //discussionRepository.
        }
        return null;
    }

    @Override
    public void deleteDiscussion(Discussion discussion) {
        discussionRepository.deleteById(discussion.getId());
    }

    @Override
    public Optional<Discussion> getDiscussion(Long discussionId) {
        return discussionRepository.findById(discussionId);
    }

    @Override
    public List<Discussion> getAllDiscussions(Long categoryId) {
        return List.of();
    }

    // TODO

    public List<Comment> getAllComments(Long dicussionId)
    {
        return null;//commentRepository.getAllBy(discussionId);
    }


    public Comment addComment(Long discussionId, Comment comment) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new RuntimeException("Discussion not found"));
        comment.setDiscussion(discussion);
        //return commentRepository.save(comment);
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // TODO delete

    // TODO update
}
