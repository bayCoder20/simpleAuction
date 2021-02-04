package org.example.auction.service;

import org.example.auction.model.Item;
import org.example.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public Item save(Item item) {
		itemRepository.save(item);
		return item;
	}
	
	@Override
	public Item findById(Long id) {
		if(itemRepository.findById(id).isPresent()) {
			return itemRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Item item = findById(id);
		itemRepository.delete(item);
	}
}
