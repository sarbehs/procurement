package com.rest.resources;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rest.core.Constants;
import com.service.StaticService;

import io.swagger.annotations.Api;
@Component
@Path(Constants.STATIC)
@Api(value = Constants.REST_API_NAME_STATIC)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaticResource {

	@Autowired
	private StaticService staticService;

	// hot sku
	@GET
	@Path(Constants.HOT_SKU)
	public Response hotSku() throws JsonParseException, JsonMappingException, IOException {
		Map<String, Integer> sku = staticService.hotSku();
		return Response.status(Response.Status.OK).entity(sku).build();
	}

	// hot catalog
	@GET
	@Path(Constants.HOT_CATALOG)
	public Response hotCatalog() throws JsonParseException, JsonMappingException, IOException {
		Map<Integer, Integer> catalog = staticService.hotCatalog();
		return Response.status(Response.Status.OK).entity(catalog).build();
	}

	// order history
	@GET
	@Path(Constants.USER_ID)
	public Response getHistory(@PathParam("userId") Integer userId)
			throws JsonParseException, JsonMappingException, IOException {
		Map<Integer, Integer> items = staticService.getHistory(userId);
		return Response.status(Response.Status.OK).entity(items).build();
	}
}
