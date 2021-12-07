package com.example.demo.service.categoryService;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    void delete(int idCategory);

    Category findById(int idCategory);

    List<Category> findByName(String nameCategory);
}
