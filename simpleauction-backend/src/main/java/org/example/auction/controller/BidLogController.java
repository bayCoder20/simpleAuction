package org.example.auction.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.example.auction.exception.GenericException;
import org.example.auction.exception.InvalidInputException;
import org.example.auction.model.BidLog;
import org.example.auction.service.BidLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simpleauction/")
public class BidLogController {
	
	@Autowired 
	BidLogService bidLogService;
	
	@GetMapping("/bidLogs")
	@Transactional
	@CrossOrigin
	public ResponseEntity<ArrayList<Map<String, Object>>> get(){
		List<BidLog> bidLogs = bidLogService.findAll();
		Map<String, Object>  map = null;
		ArrayList<Map<String, Object>>  mapList = new ArrayList<Map<String, Object>>();
		Date dateTime = null;
		String dateTimeString = "";
		for (BidLog bidLog : bidLogs) {
			map = new LinkedHashMap<String, Object>();
			map.put("bidLogId", bidLog.getBidLogId());
			map.put("auctionItemId", bidLog.getAuctionItemId());
			map.put("bidAmount", bidLog.getBidAmount());
			map.put("bidderName", bidLog.getBidderName());
			map.put("maxAutoBidAmount", bidLog.getMaxAutoBidAmount());
			map.put("comment", bidLog.getComment());
			dateTime = bidLog.getCreatedDate();
			dateTimeString  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
			map.put("createdDate", dateTimeString);
			mapList.add(map);
        }
		return new ResponseEntity<ArrayList<Map<String, Object>>>(mapList, HttpStatus.OK);
	}
	
	@PostMapping("/bidLogs")
	@Transactional
	@CrossOrigin
	public ResponseEntity<Object> save(@RequestBody BidLog bidLog) {
		try {
			BidLog bidLogsOne = bidLogService.save(bidLog);
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("bidLogId", bidLogsOne.getBidLogId());
	        return new ResponseEntity<Object>(map, HttpStatus.OK);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getClass().getSimpleName().toLowerCase());
			if(e.getClass().getSimpleName().toLowerCase().contains("sql")){
				throw new InvalidInputException();
			}
			else {
				throw new GenericException();
			}
		}
    }
	
}
