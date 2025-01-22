package com.keschubay.discussions.service;

import com.keschubay.discussions.model.Category;
import com.keschubay.discussions.repository.CategoryRepository;
import com.keschubay.discussions.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }
}
