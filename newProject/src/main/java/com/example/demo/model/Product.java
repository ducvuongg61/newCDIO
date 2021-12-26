package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private int idProduct;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    private Category category;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account", referencedColumnName = "idAccount")
    private Account accounts;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private Set<Color> color;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL)
    private Auction auction;

    @OneToMany(mappedBy = "product")
    private Set<ProductBill> productBills;

    private String productName;
    private String image1;
    private String image2;
    private String image3;
    @Column(length = 2500)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePost;
    private String status;

    public Product() {
    }

    public Product(int idProduct, Category category, Account accounts, Set<Comment> comments, Set<Color> color, Auction auction, Set<ProductBill> productBills, String productName, String image1, String image2, String image3, String description, Date datePost, String status) {
        this.idProduct = idProduct;
        this.category = category;
        this.accounts = accounts;
        this.comments = comments;
        this.color = color;
        this.auction = auction;
        this.productBills = productBills;
        this.productName = productName;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.description = description;
        this.datePost = datePost;
        this.status = status;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Color> getColor() {
        return color;
    }

    public void setColor(Set<Color> color) {
        this.color = color;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Set<ProductBill> getProductBills() {
        return productBills;
    }

    public void setProductBills(Set<ProductBill> productBills) {
        this.productBills = productBills;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}