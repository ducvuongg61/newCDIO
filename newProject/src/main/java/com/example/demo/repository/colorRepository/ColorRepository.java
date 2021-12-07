package com.example.demo.repository.colorRepository;

import com.example.demo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
}
