package org.example.auction.service;

import java.util.List;

import org.example.auction.model.BidLog;

public interface BidLogService {
	List<BidLog> findAll();
	
	BidLog save(BidLog bidLog);
}
