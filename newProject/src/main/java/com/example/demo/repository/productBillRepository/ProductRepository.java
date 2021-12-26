package com.example.demo.repository.productBillRepository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//    List<Product> findByStatusAndCategory_IdCategoryOrderByPrice(String status , int category);

    @Query("select p from  Product p where p.accounts.idAccount= ?1")
    List<Product> findProduct(String idAccount);

    List<Product> findAllByStatusContaining(String status);

    List<Product> findByProductNameContains(String nameProduct);

    List<Product> findByStatusAndProductNameContains(String status,String nameProduct);

    @Query("select p from  Product p where p.status = ?1 and p.category.idCategory = ?2 and p.productName like %?3%")
    List<Product> findByStatusAndCategory_IdCategoryAndProductNameContainsOrderByPrice(String status, Integer idCategory, String nameProduct);

    @Query("select p from  Product p where p.status = ?1 and p.category.idCategory = ?2 ")
    List<Product> findByStatusAndCategory_IdCategoryOrderByPrice(String status, Integer idCategory);

    @Query("select p from  Product p where p.status = ?1")
    List<Product> findAllApproved(String status);

    @Query("select p from Product p where p.status = ?1 and p.accounts.idAccount = ?2")
    List<Product> findAllByNotApprovedYet(String status , String idAccount);

    @Query("select p from  Product p where p.accounts.idAccount= ?1")
    List<Product> findAccount(String idAccount);

    @Query("select p from  Product p where p.status = ?1  and p.accounts.idAccount = ?2 and p.productName like %?3%")
    List<Product> findByProductNameNotApprovedYet(String status, String idAccount, String nameProduct);

    @Query("select p from  Product p where p.accounts.idAccount= ?1 and p.productName like %?2%")
    List<Product> findByMyNameProduct(String idAccount, String nameProduct);

    @Query("select p from  Product p,Auction a where p.status = ?1 and p.category.idCategory = ?2 and p.idProduct=a.product.idProduct and p.idProduct=a.product.idProduct ")
    List<Product> findByStatusAndCategory_IdCategoryAndAuction_IdProductOrderByPrice(String status, Integer idCategory);

}
