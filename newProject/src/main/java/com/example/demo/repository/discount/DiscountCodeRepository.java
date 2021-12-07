package com.example.demo.repository.discount;

import com.example.demo.model.Discount;
import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode , Integer> {
    List<DiscountCode> findDiscountCodeByDiscount_NameDiscount(String idDiscount);

    List<DiscountCode> findDiscountCodeByDiscount_NameDiscountAndDiscountStatus_IdStatus(String idDiscount , int statusId);
}
