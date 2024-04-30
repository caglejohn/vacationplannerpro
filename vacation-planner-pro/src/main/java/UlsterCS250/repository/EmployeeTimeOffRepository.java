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
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT * FROM EmployeeTimeOffs ORDER BY employee_time_off_id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                timeOffList.add(makeEmployeeTimeOff(rs));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee time off data", e);
            e.printStackTrace();
        }
        return timeOffList;
    }

    public ArrayList<String> findByUser(int id) {
        ArrayList<String> timeOffList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn
                    .prepareStatement(
                            "SELECT hd.start_date \n" + //
                                    "FROM EmployeeTimeOffs eto \n" + //
                                    "JOIN HalfDays hd ON eto.half_day_id = hd.half_day_id \n" + //
                                    "WHERE eto.employee_id = ? \n" + //
                                    "ORDER BY hd.start_date");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                timeOffList.add(rs.getDate("start_date").toString());
        } catch (SQLException e) {
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

    public void addDayOff(JEmployeeTimeOff timeOff) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn
                    .prepareStatement("INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, reason) VALUES (?,?,?)");
            stmt.setInt(1, timeOff.getEmployeeId());
            stmt.setInt(2, timeOff.getHalfDayId());
            stmt.setString(3, timeOff.getReason());
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
                rs.getString("reason"));
    }

    public void addTimeOff(int employeeId, int halfDayId, String reason) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, reason) VALUES (?, ?, ?)");
            stmt.setInt(1, employeeId);
            stmt.setInt(2, halfDayId);
            stmt.setString(3, reason);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0)
                LOGGER.info("Time off added successfully");
            else
                LOGGER.warning("Failed to add time off");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while adding employee time off", e);
        }
    }
}
