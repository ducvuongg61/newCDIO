package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idColor;
    private String color;
    private int quantity;
    private String status;
    private double price;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account", referencedColumnName = "idAccount")
    private Account accounts;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "idProduct" , referencedColumnName = "idProduct")
    private Product product;

    public Color() {
    }

    public Color(int idColor, String color, int quantity, String status, double price, Account accounts, Product product) {
        this.idColor = idColor;
        this.color = color;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
        this.accounts = accounts;
        this.product = product;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
