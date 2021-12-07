package com.example.demo.repository.discount;

import com.example.demo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount , Integer> {
//    Discount findByNameDiscount(String nameDiscount);
}
