package com.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.model.Item;
import com.model.OrderWithBLOBs;

@Component("StaticService")
public class StaticServiceImp implements StaticService {

	@Autowired
	OrderService orderService;
	@Autowired
	ItemService itemService;

	@Override
	public Map<Integer, Integer> getHistory(Integer userId)
			throws JsonParseException, JsonMappingException, IOException {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		OrderWithBLOBs owb = new OrderWithBLOBs();
		owb.setUserId(userId);
		List<OrderWithBLOBs> os = orderService.getOrders(owb);

		for (OrderWithBLOBs o : os) {
			String items = o.getItems();
			ObjectMapper mapper = new ObjectMapper();
			Integer[] results = mapper.readValue(items,
					TypeFactory.defaultInstance().constructArrayType(Integer.class));
			for (Integer i : results) {
				if (map.containsKey(i)) {
					map.put(i, map.get(i) + 1);
				} else {
					map.put(i, 1);
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Integer> hotSku() throws JsonParseException, JsonMappingException, IOException {
		List<OrderWithBLOBs> all = orderService.getOrders(new OrderWithBLOBs());

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (OrderWithBLOBs o : all) {
			String items = o.getItems();
			ObjectMapper mapper = new ObjectMapper();
			Integer[] results = mapper.readValue(items,
					TypeFactory.defaultInstance().constructArrayType(Integer.class));
			for (Integer i : results) {
				Item tmp = itemService.getItemById(i);
				if (map.containsKey(tmp.getSku())) {
					map.put(tmp.getSku(), map.get(tmp.getSku()) + 1);
				} else {
					map.put(tmp.getSku(), 1);
				}
			}
		}
		return map;
	}

	@Override
	public Map<Integer, Integer> hotCatalog() throws JsonParseException, JsonMappingException, IOException {
		List<OrderWithBLOBs> all = orderService.getOrders(new OrderWithBLOBs());

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (OrderWithBLOBs o : all) {
			String items = o.getItems();
			ObjectMapper mapper = new ObjectMapper();
			Integer[] results = mapper.readValue(items,
					TypeFactory.defaultInstance().constructArrayType(Integer.class));
			for (Integer i : results) {
				Item tmp = itemService.getItemById(i);
				if (map.containsKey(tmp.getCatalog())) {
					map.put(tmp.getCatalog(), map.get(tmp.getCatalog()) + 1);
				} else {
					map.put(tmp.getCatalog(), 1);
				}
			}
		}
		return map;
	}

	/*
	 * public static void main(String[] args) throws JsonParseException,
	 * JsonMappingException, IOException { String items = "[123, 345, 567]";
	 * ObjectMapper mapper = new ObjectMapper(); String[] lists =
	 * mapper.readValue(items,
	 * TypeFactory.defaultInstance().constructArrayType(String.class));
	 * 
	 * for (String i : lists) { System.out.println(i); } }
	 */
}
