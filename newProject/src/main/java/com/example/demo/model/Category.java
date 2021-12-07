package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCategory")
    private int idCategory;

//    @NotEmpty(message = "vui lòng nhập tên danh mục")
    private String categoryName;

    private String categoryImg;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }

    public Category(int idCategory, String categoryName, String categoryImg, Set<Product> products) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
        this.products = products;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
