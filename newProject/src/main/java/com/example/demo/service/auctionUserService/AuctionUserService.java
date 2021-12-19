package com.example.demo.service.auctionUserService;

import com.example.demo.model.AuctionUser;

import java.util.List;

public interface AuctionUserService {
    List<AuctionUser> findByProduct(int id);

    void create(AuctionUser auctionUser);
}
