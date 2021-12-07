package com.example.demo.service.discountService;

import com.example.demo.model.Discount;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.DiscountStatus;
import com.example.demo.repository.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    DiscountCodeServiceImpl discountCodeService;

    @Autowired
    DiscountStatusService discountStatusService;

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public void save(Discount discount) {
        discountRepository.save(discount);
        DiscountStatus discountStatus = new DiscountStatus(1);
        for (int i = 0; i < discount.getQuantity(); i++) {
            int n = 10000 + new Random().nextInt(90000);
            DiscountCode discountCode = new DiscountCode(n, discountRepository.findById(discount.getIdDiscount()).orElse(null), discountStatus);
            discount.generateCode(discountCode);
            discountCodeService.save(discountCode);
        }
        discountRepository.save(discount);
    }

    @Override
    public Discount findById(int idDiscount) {
        return discountRepository.findById(idDiscount).orElse(null);
    }

    @Override
    public void delete(int idDiscount) {
        discountRepository.deleteById(idDiscount);
    }

    @Override
    public void pickDiscount(Discount discount, Integer usedCode) {
        List<DiscountCode> discountCodeList = discountCodeService.findAllCodeByNameDiscount(discount.getNameDiscount());
        for (DiscountCode code : discountCodeList) {
            if (code.getCode().equals(usedCode)) {
                code.setDiscountStatus(new DiscountStatus(2, "used"));
            }
        }
        discount.pickDiscount();
        discountRepository.save(discount);
    }
}
