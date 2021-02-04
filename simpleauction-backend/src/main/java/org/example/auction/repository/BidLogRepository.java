package org.example.auction.repository;

import org.example.auction.model.BidLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidLogRepository extends JpaRepository<BidLog, Long>{

}
