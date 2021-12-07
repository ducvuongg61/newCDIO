package com.example.demo.service.product;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void delete(int id);

    List<Product> findProduct(String idAccount);

    List<Product> findByNameApproved(String status, String nameProduct);

    List<Product> findByCategory(String status, int idCategory);

    List<Product> findAccount(String idAccount);

    Page<Product> findAllProduct(Pageable pageable);

    List<Product> findByStatus(String status);

    List<Product> findByName(String productName);

    List<Product> findByCategoryAndNameProduct(String status, Integer idCategory, String nameProduct);

    List<Product> findByCategory(String status, Integer idCategory);

    List<Product> findByApproved(String status);

    List<Product> findAllByNotApprovedYet(String status , String idAccount);

    List<Product> findByNotApprovedYet(String status, String idAccount, String nameProduct);

    List<Product> findByNameProduct(String idAccount, String nameProduct);
}
