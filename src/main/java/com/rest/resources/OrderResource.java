package com.rest.resources;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.OrderWithBLOBs;
import com.rest.core.Constants;
import com.service.OrderService;

import io.swagger.annotations.Api;

@Component
@Path(Constants.ORDER)
@Api(value = Constants.REST_API_NAME_ORDER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

	@Autowired
	OrderService orderService;
	
	@GET
	@Path(Constants.ORDERS)
	
	public Response getOrders(@QueryParam("userId") Integer userId) {
		List<OrderWithBLOBs> orders = orderService.getOrdersByUserId(userId);
		return Response.status(Response.Status.OK).entity(orders).build();
	}
	
	@GET
	@Path(Constants.ORDER_ID)
	public Response getOrder(@PathParam("id") Integer id) {
		// need to add validation and exception logic later
		OrderWithBLOBs order = orderService.getOrderById(id);
		return Response.status(Response.Status.OK).entity(order).build();
	}
	
	@PUT
	@Path(Constants.ORDER_ID)
	public Response updateOrder(@PathParam("id") Integer id, OrderWithBLOBs order) {
		orderService.updateOrder(id, order);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path(Constants.ORDER_ID)
	public Response removeOrder(@PathParam("id") Integer id) {
		OrderWithBLOBs order = orderService.getOrderById(id);
		orderService.removeOrder(order);
		return Response.status(Response.Status.OK).entity(order).build();
	}
	
	@POST
	public Response createOrder(OrderWithBLOBs order) {
		orderService.createOrder(order);
		return Response.status(Response.Status.CREATED).build();
	}
	
}
