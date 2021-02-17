package org.example.auction.service;

import java.util.List;

import org.example.auction.model.Auction;
import org.example.auction.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuctionServiceImpl implements AuctionService {
	
	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}
	
	@Override
	public Auction save(Auction auction) {
		auctionRepository.save(auction);
		return auction;
	}

	@Override
	public Auction findById(Long id) {
		if(auctionRepository.findById(id).isPresent()) {
			return auctionRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Auction auction = findById(id);
		auctionRepository.delete(auction);
	}

	@Override
	public void updateForBid(Long id, String currentBid, String maxAutoBidAmount, String bidderName) {
		auctionRepository.updateBid(id, currentBid, maxAutoBidAmount, bidderName);
	}

}
