package com.aj.basicdropwizard.resource;

import com.aj.basicdropwizard.domain.PingRequest;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ping", description = "Ping Resource for checking if application is up")
public class PingResource {

    private static final Logger logger = LoggerFactory.getLogger(PingResource.class);

	@Timed
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value ="ping")
	public Response ping() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "pong");
		return Response.ok(response).build();
	}

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response send(PingRequest pingRequest) throws Exception {
        logger.info("Request received is: " + pingRequest );
        Map<String, String> response = new HashMap<>();
        response.put("message", "");
        if("ping".equalsIgnoreCase(pingRequest.getInput())) {
            response.put("message", "pong");
        }
        return Response.ok(response).build();
    }

    @Timed
    @GET
    @Path("/async")
    @Produces(MediaType.APPLICATION_JSON)
    public Response async() {
        Map<String, String> response = new HashMap<>();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                // perform heavyweight task
                Thread.sleep(15 * 1000);
                logger.info("Processing complete");
            } catch (InterruptedException ie) {
                logger.error("Error in PingResource.async(): {}", ie.getMessage());
            }
        });
        response.put("message", "Request is under process");
        return Response.ok(response).build();
    }
}
