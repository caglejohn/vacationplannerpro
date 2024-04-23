package UlsterCS250.rest;

import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.repository.EmployeeTimeOffRepository;
import UlsterCS250.requests.TimeOffRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    private EmployeeRepository employeeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "404", description = "No half-days found"),
            @APIResponse(responseCode = "200", description = "Success"),
            @APIResponse(responseCode = "500", description = "Internal server error")
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Invalid request"),
            @APIResponse(responseCode = "404", description = "User not found"),
            @APIResponse(responseCode = "201", description = "Created"),
            @APIResponse(responseCode = "500", description = "Internal server error") })
    public Response postEmployeeTimeOff(TimeOffRequest req, @CookieParam("sessionId") String sessionId) {
        try {
            if (sessionId == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid request")
                        .build();
            }
            int employeeId = employeeRepository.getEmpIdBySessionId(sessionId);
            if (employeeId == 0) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("User not found")
                        .build();
            }
            /*
             * -> call to EmployeeTimeOffRepository using:
             * data for employeeId using id grabbed above
             * data for halfdayId found by looking up the halfday id-
             * given the date and time of day sent in TimeOffRequest
             */

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error: " + e.getMessage())
                    .build();
        }
    }
}
