package UlsterCS250.repository;

import UlsterCS250.entities.JEmployeeTimeOff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.*;
public class EmployeeTimeOffRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public ArrayList<JEmployeeTimeOff> findAll() {
        ArrayList<JEmployeeTimeOff> timeOffList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EmployeeTimeOffs ORDER BY employee_time_off_id");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) timeOffList.add(makeEmployeeTimeOff(rs));
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee time off data", e);
            e.printStackTrace();
        }
        return timeOffList;
    }

    public boolean isDateFree(Date date) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM EmployeeTimeOff WHERE date = ?");
            stmt.setString(1, date.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count == 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to retrieve vacation data", e);
            return false;
        }
    }

    public void addDayOff(Long id, Long employeeId, Date date) throws SQLException {
        if (isDateFree(date)) {
            throw new SQLException("Day already requested off");
        }
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO EmployeeTimeOff (id, employee_id, date, is_am, is_pm, is_personal) VALUES (?,?,?,?,?,?)");
            stmt.setLong(1, id);
            stmt.setLong(2, employeeId);
            stmt.setString(3, date.toString());
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, true);
            stmt.setBoolean(6, true);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Vacation time added successfully");
            } else {
                LOGGER.warning("Failed to add vacation");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while adding vacation", e);
        }
    }
    public static JEmployeeTimeOff makeEmployeeTimeOff(ResultSet rs) throws SQLException {
        return new JEmployeeTimeOff(
                rs.getInt("employee_time_off_id"),
                rs.getInt("employee_id"),
                rs.getInt("half_day_id"),
                rs.getString("reason")
        );
    }
}
