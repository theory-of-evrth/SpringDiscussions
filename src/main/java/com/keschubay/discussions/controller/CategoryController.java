package com.keschubay.discussions.controller;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.service.CategoryService;
import com.keschubay.discussions.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable)
    {
        return ResponseEntity.ok(toPage(categoryService.getAllCategories(), pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestParam String name)
    {
        Category category = new Category();
        category.setName(name);
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/category/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable Long categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Category> editCategoryById(@PathVariable Long categoryId, @RequestParam String newName)
    {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            category.get().setName(newName);
            return ResponseEntity.ok(categoryService.editCategory(category.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long categoryId)
    {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            categoryService.deleteCategory(category.get());
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    private <T> Page<T> toPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        List<T> subList = list.subList(start, end);
        return new PageImpl<>(subList, pageable, list.size());
    }
}
