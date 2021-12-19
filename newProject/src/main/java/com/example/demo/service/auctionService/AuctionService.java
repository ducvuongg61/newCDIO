package com.example.demo.service.auctionService;

import com.example.demo.model.Auction;

public interface AuctionService {
    Auction findByProduct(int id);


    void save(Auction auction);
}
