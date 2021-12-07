package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuction;
    private double priceJump;
    private String startDate;
    private String endDate;

    @OneToOne
    private Product product;

    public Auction() {
    }

    public Auction(int idAuction, double priceJump, String startDate, String endDate, Product product) {
        this.idAuction = idAuction;
        this.priceJump = priceJump;
        this.startDate = startDate;
        this.endDate = endDate;
        this.product = product;
    }

    public int getIdAuction() {
        return idAuction;
    }

    public void setIdAuction(int idAuction) {
        this.idAuction = idAuction;
    }

    public double getPriceJump() {
        return priceJump;
    }

    public void setPriceJump(double priceJump) {
        this.priceJump = priceJump;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
