package com.example.demo.repository.auctionUserRepository;

import com.example.demo.model.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionUserRepository extends JpaRepository<AuctionUser,Integer> {
    List<AuctionUser> findByAuctions_Product_IdProductOrderByStartingPriceDesc(int id);
}
