package UlsterCS250.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.*;

import UlsterCS250.entities.EmployeeTimeOff;

public class EmployeeTimeOffRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public ArrayList<EmployeeTimeOff> findAll() {
        ArrayList<EmployeeTimeOff> timeOffList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EmployeeTimeOffs ORDER BY id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()){
                        EmployeeTimeOff timeOff = new EmployeeTimeOff(
                        rs.getLong("id"),
                        rs.getLong("employee_id"),
                        rs.getDate("date"),
                        Boolean.parseBoolean(rs.getString("is_am")),
                        Boolean.parseBoolean(rs.getString("is_pm")),
                        Boolean.parseBoolean(rs.getString("is_personal"))
                        );
                        timeOffList.add(timeOff);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employees", e);
            e.printStackTrace();
        }
        return timeOffList;
    }

    public boolean isDateTaken(Date date) {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM EmployeeTimeOff WHERE date = ?")){
            stmt.setDate(1, Date date);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);
                return count == 0;
            }
         }   
    }

    public void addDayOff(Long id, Long employeeId, Date date) throws SQLException {
        if (!isDateTaken(date)) {
            throw new SQLException("Day already requested off");
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO EmployeeTimeOff (id, employee_id, date, is_am, is_pm, is_personal) VALUES (?,?,?,?,?,?)")) {
    
            stmt.setLong(1, id);
            stmt.setLong(2, employeeId);
            stmt.setDate(3, date);
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, true);
            stmt.setBoolean(6, true);
           
    
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Employee added successfully");
            } else {
                LOGGER.warning("Failed to add employee");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while adding employee", e);
        }
    }

    
}
