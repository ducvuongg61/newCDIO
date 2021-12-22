package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "auction_user")
public class AuctionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuctionUser;

    @ManyToOne(targetEntity = Auction.class)
    @JoinColumn(name = "idAuction")
    private Auction auctions;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private AccUser users;

    private double startingPrice;
    private Time auctionEndTime;

    public AuctionUser() {
    }

    public int getIdAuctionUser() {
        return idAuctionUser;
    }

    public void setIdAuctionUser(int idAuctionUser) {
        this.idAuctionUser = idAuctionUser;
    }

    public Auction getAuctions() {
        return auctions;
    }

    public void setAuctions(Auction auctions) {
        this.auctions = auctions;
    }

    public AccUser getUsers() {
        return users;
    }

    public void setUsers(AccUser users) {
        this.users = users;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Time getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Time auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

}
