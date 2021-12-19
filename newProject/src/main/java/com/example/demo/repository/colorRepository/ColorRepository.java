package com.example.demo.repository.colorRepository;

import com.example.demo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findAllByProduct_IdProduct(int idProduct);

    Color findByProduct_IdProduct(int idProduct);
}
