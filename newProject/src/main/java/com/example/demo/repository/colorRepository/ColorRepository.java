package com.example.demo.repository.colorRepository;

import com.example.demo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findAllByProduct_IdProduct(int idProduct);

    List<Color> findAllByProduct_Accounts_IdAccount(String idAccount);

    List<Color> findAllByProduct_StatusAndProduct_Accounts_IdAccount(String status , String idAccount);

    @Query("select c , min(c.price) as min from Color c inner join Product p on p.idProduct = c.product.idProduct where p.status = ?1 and p.category.idCategory = ?2 group by c.product.idProduct")
    List<Color> findByProduct_StatusAndProduct_Category_IdCategory(String status , Integer category);

    List<Color> findByProduct_Status(String status);
}
