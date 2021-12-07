package com.example.demo.service.discountService;

import com.example.demo.model.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();

    void save(Discount discount);

    Discount findById(int idDiscount);

    void delete(int idDiscount);

    void pickDiscount(Discount discount , Integer usedCode);
}
