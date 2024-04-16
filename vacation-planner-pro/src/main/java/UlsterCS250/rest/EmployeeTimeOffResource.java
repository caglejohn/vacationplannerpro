package UlsterCS250.rest;

import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.repository.EmployeeTimeOffRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.ArrayList;
import java.util.logging.Logger;

@Path("/employee-time-off")
public class EmployeeTimeOffResource {
    private EmployeeTimeOffRepository employeeTimeOffRepository;
    private static final Logger LOGGER = Logger.getLogger(EmployeeResource.class.getName());
    @Inject
    public EmployeeTimeOffResource(EmployeeTimeOffRepository employeeTimeOffRepository) {
        this.employeeTimeOffRepository = employeeTimeOffRepository;
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
    public Response getEmployeeTimeOffs() {
        try {
            ArrayList<JEmployeeTimeOff> employeeTimeOffs = employeeTimeOffRepository.findAll();
            if (employeeTimeOffs.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No employee-time-offs found")
                        .build();
            }
            return Response.ok(employeeTimeOffs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving employee-time-offs: " + e.getMessage())
                    .build();
        }
    }
}
