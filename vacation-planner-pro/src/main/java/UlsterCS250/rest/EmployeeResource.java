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
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import jakarta.inject.Inject;

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
        LOGGER.warning("Received username into API call: " + username);

        Employee employee = employeeRepository.findByUsername(username);

        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(employee).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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

}

