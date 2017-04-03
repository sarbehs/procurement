package com.service;

import java.util.List;

import com.model.Item;

public interface ItemService {
	List<Item> getAll();
	
	Item getItemById(Integer id);
	
	void updateItem(Integer id, Item item);
	
	void removeItemById(Integer id);
	
	int createItem(Item item);
}
