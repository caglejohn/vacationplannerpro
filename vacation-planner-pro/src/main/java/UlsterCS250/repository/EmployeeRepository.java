package UlsterCS250.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;

import UlsterCS250.ApplicationConfig;
import UlsterCS250.entities.JEmployee;

public class EmployeeRepository {
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());
    public ArrayList<JEmployee> findAll(boolean assignTimeOff) {
        ArrayList<JEmployee> employeesList = new ArrayList<>();
        try {
            Connection conn = Util.getAuthConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY employee_id");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) employeesList.add(makeEmployee(rs,assignTimeOff));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employees", e);
            e.printStackTrace();
        }
        return employeesList;
    }

    public JEmployee findByUsername(String username, boolean assignTimeOff) {
        try {
            Connection conn = Util.getAuthConn();PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE username ILIKE ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LOGGER.info("Found employee by username: " + username);
                return makeEmployee(rs,assignTimeOff);
            } else {
                LOGGER.warning("Employee not found with username: " + username);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee by username: " + username, e);
            return null;
        }
    }
    public ArrayList<JEmployee> findByDayOff(int index, boolean assignTimeOff) {
        ArrayList<JEmployee> jEmployees = new ArrayList<>();
        try {
            Connection conn = Util.getAuthConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees " +
                        "JOIN EmployeeTimeOffs ON Employees.id = EmployeeTimeOffs.employee_id " +
                        "WHERE EmployeeTimeOffs.half_day_id = ?");
            stmt.setInt(1,index);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) jEmployees.add(makeEmployee(rs, assignTimeOff));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return jEmployees;
    }
    public static JEmployee makeEmployee(ResultSet rs, boolean assignTimeOff) throws SQLException {
        return new JEmployee(
                rs.getInt("employee_id"),
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
                rs.getString("years_of_service"),
                assignTimeOff ? ApplicationConfig.getHalfDayRepository().findByEmployee(rs.getInt("employee_id"), false) : null
        );
    }

}
