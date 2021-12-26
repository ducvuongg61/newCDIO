package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuction;
    private double priceJump;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String auctionTime;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "auctions",cascade = CascadeType.ALL)
    private Set<AuctionUser> auctionUsers;

    public Auction() {
    }

    public Auction(int idAuction, double priceJump, Date startDate, Date endDate, Product product) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(String auctionTime) {
        this.auctionTime = auctionTime;
    }

    public Set<AuctionUser> getAuctionUsers() {
        return auctionUsers;
    }

    public void setAuctionUsers(Set<AuctionUser> auctionUsers) {
        this.auctionUsers = auctionUsers;
    }
}
