package com.example.demo.service.discountService;

import com.example.demo.model.DiscountCode;
import com.example.demo.repository.discount.DiscountCodeRepository;
import com.example.demo.repository.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService{
    @Autowired
    DiscountCodeRepository discountCodeRepository;

    @Override
    public List<DiscountCode> findAll() {
        return discountCodeRepository.findAll();
    }

    @Override
    public DiscountCode findById(int idDiscountCode) {
        return discountCodeRepository.findById(idDiscountCode).orElse(null);
    }

    @Override
    public void save(DiscountCode discountCode) {
        discountCodeRepository.save(discountCode);
    }

    @Override
    public List<DiscountCode> findAllCodeByNameDiscount(String nameDiscount) {
        return discountCodeRepository.findDiscountCodeByDiscount_NameDiscount(nameDiscount);
    }

    @Override
    public List<DiscountCode> findAvailableCodeByNameDiscount(String nameDiscount) {
        return discountCodeRepository.findDiscountCodeByDiscount_NameDiscountAndDiscountStatus_IdStatus(nameDiscount , 1);
    }

    @Override
    public List<DiscountCode> findUsedCodeByNameDiscount(String nameDiscount) {
        return discountCodeRepository.findDiscountCodeByDiscount_NameDiscountAndDiscountStatus_IdStatus(nameDiscount , 2);
    }
}
