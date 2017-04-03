package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ItemMapper;
import com.model.Item;

@Component("ItemService")
public class ItemServiceImp implements ItemService {
	
	@Autowired
	ItemMapper itemMapper;
	
	@Override
	public List<Item> getAll() {
		return itemMapper.getAll();
	}

	@Override
	public Item getItemById(Integer id) {
		Item i = new Item();
		i.setId(id);
		
		List<Item> itesms = itemMapper.select(i);
		return (itesms.size() == 0) ? null : itesms.get(0);
	}

	@Override
	public void updateItem(Integer id, Item item) {
		Item i = new Item();
		i.setId(id);
		Item record = itemMapper.select(i).get(0);
		
		record.setPrice(item.getPrice());
		record.setSku(item.getSku());
		record.setCatalog(item.getCatalog());
		record.setSupplier(item.getSupplier());
		record.setUnit(item.getUnit());
		
		itemMapper.update(record);
	}

	@Override
	public void removeItemById(Integer id) {
		Item i = new Item();
		i.setId(id);
		itemMapper.delete(i);
	}

	@Override
	public int createItem(Item item) {
		return itemMapper.insertSelective(item);
	}
}
