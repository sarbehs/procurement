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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Item;
import com.rest.core.Constants;
import com.service.ItemService;

import io.swagger.annotations.Api;

@Component
@Path(Constants.ITEM)
@Api(value = Constants.REST_API_NAME_ITEM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

	@Autowired
	private ItemService itemService;

	@GET
	@Path(Constants.ITEMS)
	public Response getItems() {
		// need to add validation and exception logic later
		List<Item> items = itemService.getAll();
		return Response.status(Response.Status.OK).entity(items).build();
	}

	@GET
	@Path(Constants.ITEM_ID)
	public Response getItem(@PathParam("id") Integer id) {
		Item item = itemService.getItemById(id);
		return Response.status(Response.Status.OK).entity(item).build();
	}
	
	@PUT
	@Path(Constants.ITEM_ID)
	public Response updateItem(@PathParam("id") Integer id, Item item) {
		itemService.updateItem(id, item);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
		
	@DELETE
	@Path(Constants.ITEM_ID)
	public Response removeItem(@PathParam("id") Integer id) {
		Item item = itemService.getItemById(id);
		itemService.removeItemById(id);
		return Response.status(Response.Status.OK).entity(item).build();
	}
	
	
	@POST
	public Response createItem(Item item) {
		itemService.createItem(item);
		return Response.status(Response.Status.CREATED).build();
	}
	
}
