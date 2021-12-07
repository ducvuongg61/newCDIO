package com.example.demo.model;

import javax.persistence.*;

@Entity
public class ProductBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductBill;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idBill")
    private Bill bill;

    public ProductBill() {
    }

    public ProductBill(int idProductBill, Product product, Bill bill) {
        this.idProductBill = idProductBill;
        this.product = product;
        this.bill = bill;
    }

    public int getIdProductBill() {
        return idProductBill;
    }

    public void setIdProductBill(int idProductBill) {
        this.idProductBill = idProductBill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
