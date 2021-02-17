package org.example.auction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Table(name = "tbl_auction")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="auctionItemId")
	private Long auctionItemId;
	
	@Column(name="currentBid", columnDefinition = "decimal(15,2)")
	private String currentBid;
	
	@Column(name="reservePrice", columnDefinition = "decimal(15,2)")
	private String reservePrice;
	
	@Column(name="maxAutoBidAmount", columnDefinition = "decimal(15,2)")
	private String maxAutoBidAmount;
	
	@Column(name="bidderName")
	private String bidderName;
	
	@OneToMany(targetEntity=Item.class, cascade = CascadeType.ALL)
	@JoinColumn(name="auctionItem_fk", referencedColumnName = "auctionItemId")
	private List<Item> item = new ArrayList<>();
	//private JSONArray item = new JSONArray();
}
