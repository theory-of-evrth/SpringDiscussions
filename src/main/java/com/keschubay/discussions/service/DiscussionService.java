package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Discussion;

import java.util.List;

public interface DiscussionService {
    public Discussion createDiscussion(Discussion discussion);
    public Discussion updateDiscussion(Discussion discussion);
    public void deleteDiscussion(Discussion discussion);
    public Discussion getDiscussion(int discussionId);
    public List<Discussion> getAllDiscussions();
}
