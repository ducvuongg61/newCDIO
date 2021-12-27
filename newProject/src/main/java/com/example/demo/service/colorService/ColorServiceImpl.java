package com.example.demo.service.colorService;

import com.example.demo.model.Color;
import com.example.demo.repository.colorRepository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Color> findAllProduct(String idAccount) {
        return colorRepository.findAllByProduct_Accounts_IdAccount(idAccount);
    }

    @Override
    public List<Color> findAllApprovedProduct(String status, String idAccount) {
        return colorRepository.findAllByProduct_StatusAndProduct_Accounts_IdAccount(status , idAccount);
    }

    @Override
    public List<Color> findProduct(String status, Integer idCategory) {
        return colorRepository.findByProduct_StatusAndProduct_Category_IdCategory(status, idCategory);
    }

    @Override
    public List<Color> findByProduct_Status(String status) {
        return colorRepository.findByProduct_Status(status);
    }
}
