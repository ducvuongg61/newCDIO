package com.example.demo.service.discountService;

import com.example.demo.model.Discount;
import com.example.demo.model.DiscountCode;

import java.util.List;

public interface DiscountCodeService {
    List<DiscountCode> findAll();

    DiscountCode findById(int idDiscountCode);

    void save(DiscountCode discountCode);

    List<DiscountCode> findAllCodeByNameDiscount(String nameDiscount);

    List<DiscountCode> findAvailableCodeByNameDiscount(String nameDiscount);

    List<DiscountCode> findUsedCodeByNameDiscount(String nameDiscount);

//    void returnDiscount(Discount book, Integer returnCode);
}
