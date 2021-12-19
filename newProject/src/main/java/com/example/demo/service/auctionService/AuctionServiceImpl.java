package com.example.demo.service.auctionService;

import com.example.demo.model.Auction;
import com.example.demo.repository.auctionRepository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    AuctionRepository auctionRepository;
    @Override
    public Auction findByProduct(int id) {
        return auctionRepository.findByProduct_IdProduct(id);
    }

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auction);
    }
}
