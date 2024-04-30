package UlsterCS250.rest;

import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.repository.EmployeeTimeOffRepository;
import UlsterCS250.repository.HalfDayRepository;
import UlsterCS250.requests.TimeOffRequest;
import UlsterCS250.util.EmailService;
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
    private static final Logger LOGGER = Logger.getLogger(EmployeeResource.class.getName());

    @Inject
    private EmailService emailService;

    @Inject
    private EmployeeTimeOffRepository employeeTimeOffRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private HalfDayRepository halfDayRepository;

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
            @APIResponse(responseCode = "404", description = "Info not found"),
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
                        .entity("Info not found")
                        .build();
            }
            int halfDayId = halfDayRepository.findHalfDayIdByDateAndTime(req.getFormattedDay(), req.getIsAm());
            if (halfDayId == 0) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Info not found")
                        .build();
            }
            employeeTimeOffRepository.addTimeOff(employeeId, halfDayId, req.getReason());

            ArrayList<String> receivers = employeeRepository.findAllManagersEmails();
            String employeeUsername = employeeRepository.getEmployeeNameById(employeeId);
            emailService.sendEmail(receivers, employeeUsername, req.getDay());

            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error: " + e.getMessage())
                    .build();
        }
    }
}
