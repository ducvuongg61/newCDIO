package com.example.demo.service.auctionService;

import com.example.demo.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {
    Auction findByProduct(int id);
    Page<Auction> findByAllPage(Pageable pageable);
    void save(Auction auction);
    void delete(int idAuction);

    Auction findById(int idAuction);
}
