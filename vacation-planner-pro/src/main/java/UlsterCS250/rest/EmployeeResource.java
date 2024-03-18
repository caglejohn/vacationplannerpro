package UlsterCS250.rest;

import UlsterCS250.planner.Employee;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;

@Path("/employees")
public class EmployeeResource {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";

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
    }
}
