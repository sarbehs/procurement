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

import com.model.ProUser;
import com.rest.core.Constants;
import com.service.UserService;

import io.swagger.annotations.Api;

@Component
@Path(Constants.USER)
@Api(value = Constants.REST_API_NAME_USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Autowired
	private UserService userService;

	@GET
	@Path(Constants.USERLOGIN)
	public Response loginUser(@QueryParam("username") String username, @QueryParam("passwd") String passwd){
		if(userService.loginUser(username, passwd))
			return Response.ok(true).build();	
		else
			return Response.ok(false).build();	
	}
	
	@GET
	@Path(Constants.USERS)
	public Response getUsers() {
		// need to add validation and exception logic later
		List<ProUser> users = userService.getAll();
		return Response.status(Response.Status.OK).entity(users).build();
	}

	@GET
	@Path(Constants.USERID)
	public Response getUser(@PathParam("id") Integer id) {
		ProUser user = userService.getUserById(id);
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
	@PUT
	@Path(Constants.USERID)
	public Response updateUser(@PathParam("id") Integer id, ProUser user) {
		userService.updateUser(id, user);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
		
	@DELETE
	@Path(Constants.USERID)
	public Response removeUser(@PathParam("id") Integer id) {
		ProUser user = userService.getUserById(id);
		userService.removeUserById(id);
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
	@POST
	public Response createUser(ProUser user) {
		String result = "success";
		int key = userService.createUser(user);
		if (key <= 0)
			return Response.ok(false).build();
		else
			return Response.ok(true).build();
	}
	
}
