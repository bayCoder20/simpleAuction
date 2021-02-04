package org.example.auction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "tbl_log")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bidLogId")
	private Long bidLogId;

	@Column(name="auctionItemId")
	private Long auctionItemId;
	
	@Column(name="bidAmount", columnDefinition = "decimal(15,2)")
	private String bidAmount;
	
	@Column(name="maxAutoBidAmount", columnDefinition = "decimal(15,2)")
	private String maxAutoBidAmount;
	
	@Column(name="bidderName")
	private String bidderName;
	
	@Column(name="comment")
	private String comment;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="createdDate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdDate;
}
