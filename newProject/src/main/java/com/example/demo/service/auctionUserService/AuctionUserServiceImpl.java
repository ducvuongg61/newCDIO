package com.example.demo.service.auctionUserService;

import com.example.demo.model.AuctionUser;

import com.example.demo.repository.auctionUserRepository.AuctionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionUserServiceImpl implements AuctionUserService{
    @Autowired
    AuctionUserRepository auctionUserRepository;
    @Override
    public List<AuctionUser> findByProduct(int id) {
        return auctionUserRepository.findByAuctions_Product_IdProductOrderByStartingPriceDesc(id);
    }

    @Override
    public void create(AuctionUser auctionUser) {
        auctionUserRepository.save(auctionUser);
    }
}
