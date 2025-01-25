package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {
    public Discussion createDiscussion(Discussion discussion);
    public Discussion updateDiscussion(Discussion discussion);
    public void deleteDiscussion(Discussion discussion);
    public Optional<Discussion> getDiscussion(int discussionId);
    public List<Discussion> getAllDiscussions();
    public Comment addComment(Long discussionId, Comment comment);
}
