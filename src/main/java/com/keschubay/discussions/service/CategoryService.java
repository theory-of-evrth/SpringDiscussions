package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Category;

import java.util.Optional;

public interface CategoryService {
    public Category createCategory(Category category);
    public Category editCategory(Category category);
    public void deleteCategory(Category category);
    public Optional<Category> getCategoryById(Long id);
}
