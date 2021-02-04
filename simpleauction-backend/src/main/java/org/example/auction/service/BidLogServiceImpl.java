package org.example.auction.service;

import java.util.List;

import org.example.auction.model.BidLog;
import org.example.auction.repository.BidLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidLogServiceImpl implements BidLogService {
	
	@Autowired
	BidLogRepository bidLogRepository;

	@Override
	public List<BidLog> findAll() {
		return bidLogRepository.findAll();
	}
	
	@Override
	public BidLog save(BidLog bidLog) {
		bidLogRepository.save(bidLog);
		return bidLog;
	}

}
