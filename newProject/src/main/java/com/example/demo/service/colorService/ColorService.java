package com.example.demo.service.colorService;

import com.example.demo.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ColorService {
    List<Color> findAll();

    Color findById(int idColor);

    void save(Color color);

    void delete(int idColor);


    List<Color> findByIdProduct(int id);

    Page<Color> findAllPage(Pageable pageable);
}
