package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {
    public Discussion createDiscussion(Discussion discussion);
    public Discussion updateDiscussion(Discussion discussion);
    public void deleteDiscussion(Discussion discussion);
    public Optional<Discussion> getDiscussion(Long discussionId);
    public List<Discussion> getAllDiscussions(Long categoryId);
    public Comment addComment(Long discussionId, Comment comment);
    public void deleteComment(Long commentId);
    public Optional<Comment> getComment(Long commentId);
    public List<Comment> getAllComments(Long discussionId);
}
