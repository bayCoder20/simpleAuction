package org.example.auction.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.example.auction.exception.GenericException;
import org.example.auction.exception.InvalidInputException;
import org.example.auction.model.Auction;
import org.example.auction.service.AuctionService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/simpleauction/")
public class AuctionController {
	
	@Autowired
	AuctionService auctionService;
	
	@GetMapping("/auctionItems")
	@Transactional
	//@CrossOrigin
	public ResponseEntity<ArrayList<Map<String, Object>>> get(HttpServletResponse response) throws IOException{
		List<Auction> auctions = auctionService.findAll();
		Map<String, Object>  map = null;
		ArrayList<Map<String, Object>>  mapList = new ArrayList<Map<String, Object>>();	
		for (Auction auction : auctions) {
			map = new LinkedHashMap<String, Object>();
			map.put("auctionItemId", auction.getAuctionItemId());
			map.put("currentBid", auction.getCurrentBid());
			map.put("bidderName", auction.getBidderName());
			map.put("reservePrice", auction.getReservePrice());
			map.put("maxAutoBidAmount", auction.getMaxAutoBidAmount());
			map.put("item", auction.getItem());
			mapList.add(map);
        }
		return new ResponseEntity<ArrayList<Map<String, Object>>>(mapList, HttpStatus.OK);
	}
	
	@PostMapping("/auctionItems")
	@Transactional
	//@CrossOrigin
	public ResponseEntity<Object> save(@RequestBody Auction auction) {
			Auction auctionOne = auctionService.save(auction);
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("auctionItemId", auctionOne.getAuctionItemId());
	        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
	
	@PostMapping("/newBids")
	@Transactional
	//@CrossOrigin
	public ResponseEntity<Object> updateBid(@RequestBody Auction bid) {		
			System.out.println(bid);
			auctionService.updateForBid(bid.getAuctionItemId(), bid.getCurrentBid(), bid.getMaxAutoBidAmount(), bid.getBidderName());
			return new ResponseEntity<Object>("New bid for " + bid.getBidderName() + "registered", HttpStatus.OK);
    }
	
	
	/*@GetMapping("/auctionItems/{auctionItemId}")
	public ResponseEntity<Auction> get(@PathVariable("auctionItemId") Long id) {
		Auction auction = auctionService.findById(id);
		return new ResponseEntity<Auction>(auction, HttpStatus.OK);
	}*/
	
	@GetMapping("/auctionItems/{auctionItemId}")
	@Transactional
	//@CrossOrigin
	public ResponseEntity<Object> get(@PathVariable("auctionItemId") Long id) {
		Auction auction = auctionService.findById(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("auctionItemId", auction.getAuctionItemId());
        map.put("currentBid", auction.getCurrentBid());
        map.put("reservePrice", auction.getReservePrice());
        map.put("item", auction.getItem());
        return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/auctionItems/{auctionItemId}")
	@Transactional
	//@CrossOrigin
	public ResponseEntity<String> delete(@PathVariable("auctionItemId") Long id) {
		auctionService.delete(id);
		return new ResponseEntity<String>("Auction was deleted successfully", HttpStatus.OK);
	}
	
	@RequestMapping("/simpleTest")
	@Transactional
	//@CrossOrigin
    public String basicTest() {
        return "For Controller Testing Example";
    }
}
