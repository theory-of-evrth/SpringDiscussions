package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.repository.CategoryRepository;
import com.keschubay.discussions.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category editCategory(Category category) {
        Optional<Category> existingCategory = repository.findById(category.getId());

        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());

            return repository.save(updatedCategory);
        } else {
            throw new IllegalArgumentException("Category with ID " + category.getId() + " not found. Try creating it first");
        }
    }

    @Override
    public void deleteCategory(Category category) {
        repository.deleteById(category.getId());
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
