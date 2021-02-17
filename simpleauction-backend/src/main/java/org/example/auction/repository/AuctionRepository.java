package org.example.auction.repository;

import java.util.List;

import org.example.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "update tbl_auction set currentBid = ?2, maxAutoBidAmount = ?3, bidderName = ?4 where auctionItemId = ?1",nativeQuery = true)
	public void updateBid(Long auctionItemId, String currentBid, String maxAutoBidAmount, String bidderName);
}

