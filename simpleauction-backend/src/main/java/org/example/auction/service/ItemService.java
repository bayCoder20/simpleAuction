package org.example.auction.service;

import org.example.auction.model.Item;

public interface ItemService {
	
	Item save(Item item);
	
	Item findById(Long id);
	
	void delete(Long id);
}
