package com.example.demo.service.colorService;

import com.example.demo.model.Color;
import com.example.demo.repository.colorRepository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(int idColor) {
        return colorRepository.findById(idColor).orElse(null);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void delete(int idColor) {
        colorRepository.deleteById(idColor);
    }


    @Override
    public List<Color> findByIdProduct(int idProduct) {
        return colorRepository.findAllByProduct_IdProduct(idProduct);
    }

    @Override
    public Page<Color> findAllPage(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }
}
