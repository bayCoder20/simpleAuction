package org.example.auction.service;

import java.util.List;

import org.example.auction.model.Auction;


public interface AuctionService {
	
	List<Auction> findAll();
	
	Auction save(Auction auction);
	
	Auction findById(Long id);
	
	void delete(Long id);
	
	void updateForBid(Long id, String currentBid, String maxAutoBidAmount, String bidderName);
	
}
