package org.example.auction.controller;

import org.example.auction.model.Item;
import org.example.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simpleauction/")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/items")
	public ResponseEntity<Item> save(@RequestBody Item item) {
		Item itemOne = itemService.save(item);
		return new ResponseEntity<Item>(itemOne, HttpStatus.OK);
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> get(@PathVariable("id") Long id) {
		Item item = itemService.findById(id);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		itemService.delete(id);
		return new ResponseEntity<String>("Item was deleted successfully", HttpStatus.OK);
	}

}
