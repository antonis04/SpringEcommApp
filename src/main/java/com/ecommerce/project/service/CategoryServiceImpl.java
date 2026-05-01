package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryReposistory;



    @Override
    public List<Category> getAllCategories() {
        return categoryReposistory.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryReposistory.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryReposistory.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource was not found"));

        categoryReposistory.delete(category);
        return "Category " + categoryId + " deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryReposistory.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource was not found"));

        category.setCategoryId(categoryId);

        savedCategory = categoryReposistory.save(category);
        return savedCategory;
    }
}
