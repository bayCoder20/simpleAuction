package org.example.auction.repository;

import java.util.List;

import org.example.auction.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
	
	//Had to add AuctionGet manually because it is not extended in this interface
	//List<AuctionGet> findAllMain();
	//@Query(value = "select * from tbl_auction a left join tbl_item b on a.auctionItemId=b.auctionItem_fk",nativeQuery = true)
	//public Auction getAuctionItem(@Param("auctionItemId")Long auctionItemId);
	
	@Query(value = "update tbl_auction set currentBid = ?2 where auctionItemId = ?1",nativeQuery = true)
	public Auction updateBid(Long auctionItemId, String currentBid);
}

