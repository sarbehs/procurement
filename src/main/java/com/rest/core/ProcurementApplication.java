package com.rest.core;

import org.glassfish.jersey.server.ResourceConfig;

import com.rest.resources.ItemResource;
import com.rest.resources.OrderResource;
import com.rest.resources.StaticResource;

public class ProcurementApplication extends ResourceConfig {
	public ProcurementApplication() {
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class); 
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	
		register(ItemResource.class);
		register(OrderResource.class);
		register(StaticResource.class);
	}
}
