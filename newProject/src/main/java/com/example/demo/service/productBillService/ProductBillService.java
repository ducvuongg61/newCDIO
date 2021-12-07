package com.example.demo.service.productBillService;

import com.example.demo.model.ProductBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductBillService {
    Page<ProductBill> findAll(Pageable pageable);

    Page<ProductBill> findByProduct_ProductNameContains(String nameProduct, Pageable pageable);

    Page<ProductBill> findByProduct_ProductNameAndBill_idAccountContains(String idAccount, String nameProducts, Pageable pageableSort);

    Page<ProductBill> findByBill_idAccountContains(String nameUser, Pageable pageableSort);
}
