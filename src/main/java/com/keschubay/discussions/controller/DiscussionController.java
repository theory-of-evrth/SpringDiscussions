package com.keschubay.discussions.controller;

import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.service.DiscussionService;
import com.keschubay.discussions.service.DiscussionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/category/{categoryId}")
    public Page<Discussion> getDiscussionsByCategory(
            @PathVariable Long categoryId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return null;
        //return discussionService.getDiscussionsByCategory(categoryId, PageRequest.of(page, size));
    }

    @PostMapping
    public Discussion createDiscussion(@RequestBody Discussion discussion) {
        return discussionService.createDiscussion(discussion);
    }
}
