package UlsterCS250.rest;

import UlsterCS250.entities.JEmployee;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.requests.EmployeeVM;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.http.HttpHeaders;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.inject.Inject;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeeRepository employeeRepository;
    // private static final Logger LOGGER =
    // Logger.getLogger(EmployeeResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "404", description = "No employees found"),
            @APIResponse(responseCode = "200", description = "Success"),
            @APIResponse(responseCode = "500", description = "Internal server error") })
    public Response getEmployees() {
        try {
            ArrayList<JEmployee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No employees found")
                        .build();
            }
            return Response.ok(employees).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving employees: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "404", description = "Employee not found"),
            @APIResponse(responseCode = "200", description = "Success") })
    public Response getEmployeeByUsername(@PathParam("username") String username) {
        JEmployee employee = employeeRepository.findByUsername(username);

        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(employee).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "409", description = "Username conflict"),
            @APIResponse(responseCode = "201", description = "Success"),
            @APIResponse(responseCode = "500", description = "Internal server error") })
    public Response createEmployee(@Context HttpHeaders headers, EmployeeVM employee) {
        try {
            employeeRepository.addEmployee(employee);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            if (e.getMessage().contains("Username already exists")) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Username already exists")
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error creating employee: " + e.getMessage())
                        .build();
            }
        }
    }

    @POST
    @Path("/session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "401", description = "Invalid user credentials"),
            @APIResponse(responseCode = "201", description = "Created"),
            @APIResponse(responseCode = "500", description = "Internal server error") })
    public Response addSession(@Context HttpHeaders headers, EmployeeVM employee) {
        try {
            boolean isValidLogin = employeeRepository.addSession(employee);
            if (isValidLogin) {
                String sessionId = employeeRepository.getSessionIdByEmpId(employee);
                NewCookie sessionCookie = new NewCookie.Builder("sessionId")
                        .value(sessionId)
                        .path("/")
                        .comment("Session cookie")
                        .maxAge(3600)
                        .build();

                return Response.status(Response.Status.CREATED)
                        .cookie(sessionCookie)
                        .build();
            }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid login credentials supplied")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while attempting login: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Session cookie not found"),
            @APIResponse(responseCode = "404", description = "Invalid session"),
            @APIResponse(responseCode = "200", description = "Session deleted"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    public Response deleteSession(@Context HttpHeaders headers, @CookieParam("sessionId") String sessionId) {
        try {
            if (sessionId == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Session cookie not found")
                        .build();
            }

            boolean deleted = employeeRepository.deleteSession(sessionId);
            if (deleted) {
                NewCookie removeCookie = new NewCookie.Builder("sessionId")
                        .path("/")
                        .maxAge(0)
                        .build();
                return Response.ok()
                        .cookie(removeCookie)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Invalid session")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting session: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Session cookie not found"),
            @APIResponse(responseCode = "404", description = "Invalid session"),
            @APIResponse(responseCode = "200", description = "Session found"),
            @APIResponse(responseCode = "500", description = "Internal server error")
    })
    public Response getSession(@Context HttpHeaders headers, @CookieParam("sessionId") String sessionId) {
        try {
            if (sessionId == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Session cookie not found")
                        .build();
            }

            boolean isAuthorized = employeeRepository.validateSession(sessionId);
            if (isAuthorized) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Invalid session")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error: " + e.getMessage())
                    .build();
        }
    }
}