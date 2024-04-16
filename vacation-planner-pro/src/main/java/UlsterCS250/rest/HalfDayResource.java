package UlsterCS250.rest;

import UlsterCS250.entities.JHalfDay;
import UlsterCS250.repository.HalfDayRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Logger;


import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/half-days")
public class HalfDayResource {
    private HalfDayRepository halfDayRepository;
    private static final Logger LOGGER = Logger.getLogger(HalfDayResource.class.getName());
    @Inject
    public HalfDayResource(HalfDayRepository halfDayRepository) {
        this.halfDayRepository = halfDayRepository;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "404",
                            description = "No half-days found"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Success"),
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal server error")
            })
    public Response getHalfDays() {
        try {
            ArrayList<JHalfDay> halfDays = halfDayRepository.findAll();
            if (halfDays.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No half-days found")
                        .build();
            }
            return Response.ok(halfDays).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving half-days: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{ind}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                @APIResponse(
                        responseCode = "404",
                        description = "half-day not found"),
                @APIResponse(
                        responseCode = "200",
                        description = "Success")
            })
    public Response getHalfDayByInd(@PathParam("ind") int username) {
        LOGGER.warning("Received username into API call: " + username);
        JHalfDay halfDay = halfDayRepository.findAll().get(0);
        if (halfDay == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(halfDay).build();
    }
}