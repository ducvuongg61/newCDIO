package com.example.demo.repository.discount;

import com.example.demo.model.DiscountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountStatusRepository extends JpaRepository<DiscountStatus , Integer> {
}
