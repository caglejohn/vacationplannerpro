package UlsterCS250.rest;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import UlsterCS250.repository.VacationDayRepository;
import UlsterCS250.responses.CalendarDay;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import UlsterCS250.util.EmailService;

@Path("/calendar")
public class CalendarResource {

    @Inject
    private VacationDayRepository vacationDayRepository;

    @Inject
    private EmailService emailService;

    private static final Logger logger = Logger.getLogger(CalendarResource.class.getName());

    /*
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * 
     * @APIResponses(
     * value = {
     * 
     * @APIResponse(
     * responseCode = "404",
     * description = "No days found"),
     * 
     * @APIResponse(
     * responseCode = "200",
     * description = "Success"),
     * 
     * @APIResponse(
     * responseCode = "500",
     * description = "Internal server error")})
     * public Response getCalendar() {
     * try {
     * List<CalendarDay> days = vacationDayRepository.getDays();
     * if (days.isEmpty()) {
     * return Response.status(Response.Status.NOT_FOUND)
     * .entity("No days found")
     * .build();
     * }
     * return Response.ok(days).build();
     * } catch (Exception e) {
     * return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
     * .entity("Error retrieving days: " + e.getMessage())
     * .build();
     * }
     * }
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Invalid month"),
            @APIResponse(responseCode = "404", description = "No days found"),
            @APIResponse(responseCode = "200", description = "Success"),
            @APIResponse(responseCode = "500", description = "Internal server error") })
    public Response getMonthCalendar(
            @QueryParam("month") int month,
            @QueryParam("year") int year) {
        try {
            if (month < 1 || month > 12) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid month value. Month should be between 1 and 12.")
                        .build();
            }

            List<CalendarDay> days = vacationDayRepository.getDaysForMonthAndYear(month, year);
            if (days.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No days found for the specified month and year.")
                        .build();
            }
            return Response.ok(days).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving days: " + e.getMessage())
                    .build();
        }
    }
}
