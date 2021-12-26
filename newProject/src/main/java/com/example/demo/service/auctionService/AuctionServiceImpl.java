package com.example.demo.service.auctionService;

import com.example.demo.model.Auction;
import com.example.demo.repository.auctionRepository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    AuctionRepository auctionRepository;
    @Override
    public Auction findByProduct(int id) {
        return auctionRepository.findByProduct_IdProduct(id);
    }

    @Override
    public Page<Auction> findByAllPage(Pageable pageable) {
        return auctionRepository.findAll(pageable);
    }

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public void delete(int idAuction) {
        auctionRepository.deleteById(idAuction);
    }

    @Override
    public Auction findById(int idAuction) {
        return auctionRepository.findById(idAuction).orElse(null);
    }

    @Override
    public Page<Auction> findByAuctionTimeContains(String auctionTime, Pageable pageableSort) {
        return auctionRepository.findAllByAuctionTimeContains(auctionTime,pageableSort);
    }
}
