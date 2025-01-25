package com.keschubay.discussions.service;

import com.keschubay.discussions.CurrentUserUtil;
import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.AppUserRepository;
import com.keschubay.discussions.repository.CategoryRepository;
import com.keschubay.discussions.repository.CommentRepository;
import com.keschubay.discussions.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CategoryRepository categoryRepository;


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
        return discussionRepository.findAllByCategory(categoryRepository.findById(categoryId).get());
    }

    public List<Comment> getAllComments(Long dicussionId)
    {
        return commentRepository.findAllByDiscussion(discussionRepository.findById(dicussionId).get());
    }


    public Comment addComment(Long discussionId, Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }
}
