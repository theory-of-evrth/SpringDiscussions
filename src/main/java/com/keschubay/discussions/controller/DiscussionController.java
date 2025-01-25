package com.keschubay.discussions.controller;

import com.keschubay.discussions.CurrentUserUtil;
import com.keschubay.discussions.model.AppUser;
import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.model.Comment;
import com.keschubay.discussions.model.Discussion;
import com.keschubay.discussions.repository.CategoryRepository;
import com.keschubay.discussions.repository.CommentRepository;
import com.keschubay.discussions.service.AppUserService;
import com.keschubay.discussions.service.CategoryServiceImpl;
import com.keschubay.discussions.service.DiscussionService;
import com.keschubay.discussions.service.DiscussionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @Qualifier("commentRepository")
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/{discussionId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long discussionId,
            @RequestParam String comment
    )
    {
        Optional<Discussion> discussion = discussionService.getDiscussion(discussionId);

        if (discussion.isPresent()) {

            Comment commentNew = new Comment();
            commentNew.setContent(comment);
            commentNew.setDiscussion(discussion.get());
            commentNew.setCreatedBy(CurrentUserUtil.getCurrentUsername());

            return ResponseEntity.ok(discussionService.addComment(discussionId, commentNew));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/{discussionId}/comments")
    public ResponseEntity<Page<Comment>> getComments(
            @PathVariable Long discussionId,
            Pageable pageable
    )
    {
        Optional<Discussion> discussion = discussionService.getDiscussion(discussionId);
        return discussion.map(value -> ResponseEntity.ok(toPage(commentRepository.findAllByDiscussion(value), pageable))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{discussionId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId, @PathVariable String discussionId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            return ResponseEntity.ok(comment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/category/{categoryId}")
    public Page<Discussion> getDiscussionsByCategory(
            @PathVariable Long categoryId, Pageable pageable
    ) {
        return toPage(discussionService.getAllDiscussions(categoryId), pageable);
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Discussion> createDiscussion(@RequestParam String title, @RequestParam String content, @PathVariable Long categoryId) {
        Optional<Category> category = categoryServiceImpl.getCategoryById(categoryId);

        if(category.isPresent()){
            Discussion discussion = new Discussion();
            discussion.setTitle(title);
            discussion.setContent(content);
            discussion.setCategory(category.get());
            discussion.setCreatedBy(CurrentUserUtil.getCurrentUsername());

            return ResponseEntity.ok( discussionService.createDiscussion(discussion));
        }

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<Discussion> getDiscussionById(@PathVariable Long discussionId) {
        Optional<Discussion> discussion = discussionService.getDiscussion(discussionId);
        return discussion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{discussionId}")
    public ResponseEntity<Discussion> deleteDiscussion(@PathVariable Long discussionId) {
        Optional<Discussion> discussion = discussionService.getDiscussion(discussionId);

        if (discussion.isPresent()) {
            discussionService.deleteDiscussion(discussion.get());
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Utility method to convert a List to a Page
    private <T> Page<T> toPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        List<T> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }

}
