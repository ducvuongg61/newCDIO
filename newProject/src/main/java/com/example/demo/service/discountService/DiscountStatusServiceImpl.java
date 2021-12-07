package com.example.demo.service.discountService;

import com.example.demo.model.DiscountStatus;
import com.example.demo.repository.discount.DiscountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountStatusServiceImpl implements DiscountStatusService{
    @Autowired
    DiscountStatusRepository discountStatusRepository;

    @Override
    public void createStatus() {
        discountStatusRepository.save(new DiscountStatus(1 , "available"));
        discountStatusRepository.save(new DiscountStatus(1 , "used"));
    }
}
