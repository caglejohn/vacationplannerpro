package UlsterCS250.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;

import UlsterCS250.entities.Employee;

public class EmployeeRepository {
    
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());


    public ArrayList<Employee> findAll() {
        ArrayList<Employee> employeesList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY employee_id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()){
                        Employee emp = new Employee(
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Boolean.parseBoolean(rs.getString("is_manager")),
                        Boolean.parseBoolean(rs.getString("is_active")),
                        rs.getString("last_login"),
                        rs.getString("created_at"),
                        rs.getString("department_id"),
                        rs.getString("years_of_service")
                        );
                        employeesList.add(emp);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employees", e);
            e.printStackTrace();
        }
        return employeesList;
    }

    public Employee findByUsername(String username) {

        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE username ILIKE ?")) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LOGGER.info("Found employee by username: " + username);
                    return new Employee(
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("is_manager"),
                        rs.getBoolean("is_active"),
                        rs.getString("last_login"),
                        rs.getString("created_at"),
                        rs.getString("department_id"),
                        rs.getString("years_of_service")
                    );
                } else {
                    LOGGER.warning("Employee not found with username: " + username);
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee by username: " + username, e);
            return null;
        }
    }
    

}
