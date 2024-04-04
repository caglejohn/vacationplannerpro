package UlsterCS250.rest;
import UlsterCS250.entities.Employee;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.viewModels.EmployeeVM;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import jakarta.inject.Inject;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/employees")
public class EmployeeResource {
    
    @Inject
    private EmployeeRepository employeeRepository;
    private static final Logger LOGGER = Logger.getLogger(EmployeeResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
         value = {
            @APIResponse(
                 responseCode = "404",
                 description = "No employees found"),
            @APIResponse(
                 responseCode = "200",
                 description = "Success"),
            @APIResponse(
                    responseCode = "500",
                    description = "Internal server error")})
    public Response getEmployees() {
        try {
            ArrayList<Employee> employees = employeeRepository.findAll();
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
    @APIResponses(
         value = {
             @APIResponse(
                 responseCode = "404",
                 description = "Employee not found"),
             @APIResponse(
                 responseCode = "200",
                 description = "Success")})
    public Response getEmployeeByUsername(@PathParam("username") String username) {
        LOGGER.info("Received username into API call: " + username);

        Employee employee = employeeRepository.findByUsername(username);

        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(employee).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
     value = {
         @APIResponse(
             responseCode = "409",
             description = "Username conflict"),
         @APIResponse(
             responseCode = "201",
             description = "Success"),
         @APIResponse(
             responseCode = "500",
             description = "Internal server error")})
    public Response createEmployee(EmployeeVM employee) {
        try {
            String username = employee.getUsername();
            String password = employee.getPassword();

            employeeRepository.addEmployee(username, password);
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

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Employee> getEmployeesList(){
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users ORDER BY user_id")) {
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                ArrayList<Employee> employeesList = new ArrayList<>();
                while(rs.next()){
                    Employee emp = new Employee(rs.getString(5),rs.getString(4),10001);
                    employeesList.add(emp);
                }
                return employeesList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/


@POST
@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@APIResponses(
     value = {
         @APIResponse(
             responseCode = "404",
             description = "Invalid Username or Password"),
         @APIResponse(
             responseCode = "201",
             description = "Created"),
         @APIResponse(
             responseCode = "500",
             description = "Internal server error")})
public Response addSession(EmployeeVM employee) {
    LOGGER.info("Received session into API call: " + employee.getUsername() + employee.getPassword()) ;
    try
    {
        LOGGER.warning("Received session into API call: " + employee.getUsername() + employee.getPassword()) ;

        employeeRepository.addSession(employee);
        NewCookie authTokenCookie = new NewCookie.Builder("authToken")
                .value("valid_token")
                .path("/")
                .comment("Session cookie")
                .maxAge(3600)
                .build();

        return Response.status(Response.Status.CREATED)
                .cookie(authTokenCookie)
                .build();
    }
    catch (SQLException e)
    {
        if (e.getMessage().contains("Unauthorized")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid login credentials supplied")
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while attempting login: " + e.getMessage())
                    .build();
        }
    }
}
}


/*
@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response LogIn(EmployeeVM employee) {
        try {
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            if (e.getMessage().contains("")) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("")
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(" " + e.getMessage())
                        .build();
            }
        }
    }

*/
