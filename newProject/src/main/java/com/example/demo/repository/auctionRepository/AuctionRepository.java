package com.example.demo.repository.auctionRepository;

import com.example.demo.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    Auction findByProduct_IdProduct(int id);

}
