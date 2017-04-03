package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.ProOrderMapper;
import com.model.OrderWithBLOBs;

@Component("OrderService")
public class OrderServiceImp implements OrderService {

	@Autowired
	ProOrderMapper orderMapper;
	
	@Override
	public List<OrderWithBLOBs> getOrdersByUserId(Integer userId) {
		OrderWithBLOBs order = new OrderWithBLOBs();
		order.setUserId(userId);

		return orderMapper.select(order);
	}

	@Override
	public OrderWithBLOBs getOrderById(Integer id) {
		OrderWithBLOBs order = new OrderWithBLOBs();
		order.setId(id);
		List<OrderWithBLOBs> results = orderMapper.select(order);
		return results.size() == 0 ? null : results.get(0);
	}

	@Override
	public void updateOrder(Integer id, OrderWithBLOBs order) {
		OrderWithBLOBs record = new OrderWithBLOBs();
		record.setId(id);
		List<OrderWithBLOBs> results = orderMapper.select(record);
		record = results.size() == 0 ? null : results.get(0);
		
		record.setUserId(order.getUserId());
		record.setStatus(order.getStatus());
		record.setCreateTime(order.getCreateTime());
		record.setItems(order.getItems());
		record.setJustification(order.getJustification());
		
		orderMapper.update(record);
	}

	@Override
	public List<OrderWithBLOBs> getOrders(OrderWithBLOBs order) {
		return orderMapper.select(order);
	}

	@Override
	public void removeOrder(OrderWithBLOBs order) {
		orderMapper.delete(order);
	}

	@Override
	public int createOrder(OrderWithBLOBs order) {
		return orderMapper.insertSelective(order);
	}

}
