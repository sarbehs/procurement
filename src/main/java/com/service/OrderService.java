package com.service;

import java.util.List;

import com.model.OrderWithBLOBs;

public interface OrderService {
	List<OrderWithBLOBs> getOrdersByUserId(Integer userId);	
	OrderWithBLOBs getOrderById(Integer id);
	
	List<OrderWithBLOBs> getOrders(OrderWithBLOBs order);
	void updateOrder(Integer id, OrderWithBLOBs order);
	void removeOrder(OrderWithBLOBs order);
	int createOrder(OrderWithBLOBs order);
}
