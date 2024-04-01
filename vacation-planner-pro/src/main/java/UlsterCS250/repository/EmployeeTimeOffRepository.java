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
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public ArrayList<EmployeeTimeOff> findAll() {
        ArrayList<EmployeeTimeOff> timeOffList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()){
                        EmployeeTimeOff timeOff = new EmployeeTimeOff(
                        rs.getLong("id"),
                        rs.getLong("employeeId"),
                        rs.getDate("date"),
                        Boolean.parseBoolean(rs.getString("isAm")),
                        Boolean.parseBoolean(rs.getString("isPm")),
                        Boolean.parseBoolean(rs.getString("isPersonal"))
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
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM EmployeesTimeOff WHERE date = ?")
        return date;

    }

    public void addDayOff(Long id, Long employeeId, Date date) throws SQLException {
        if (!isDateTaken(date)) {
            throw new SQLException("Day already requested off");
        }

        try (Connection conn = DriverManager.getConnection();
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
