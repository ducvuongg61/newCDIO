package com.example.demo.repository.categoryRepository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByCategoryName(String name);
}
