package UlsterCS250.repository;

import UlsterCS250.ApplicationConfig;
import UlsterCS250.entities.JHalfDay;
import UlsterCS250.planner.HalfDay;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HalfDayRepository {
    private static final Logger LOGGER = Logger.getLogger(HalfDayRepository.class.getName());

    public ArrayList<JHalfDay> findAll(boolean assignTakenOff) {
        ArrayList<JHalfDay> halfDayList = new ArrayList<>();
        try {
            Connection conn = Util.getAuthConn();
            ResultSet rs = conn.prepareStatement("SELECT * FROM HalfDays ORDER BY half_day_id").executeQuery();
            while(rs.next()) halfDayList.add(makeHalfDay(rs,assignTakenOff));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return halfDayList;
    }
    public ArrayList<JHalfDay> findByEmployee(int id, boolean assignTakenOff) {
        ArrayList<JHalfDay> halfDayList = new ArrayList<>();
        try {
            Connection conn = Util.getAuthConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HalfDays " +
                    "JOIN EmployeeTimeOffs ON EmployeeTimeOffs.half_day_id = HalfDays.half_day_id " +
                    "WHERE EmployeeTimeOffs.employee_id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.first());
            while(rs.next()) halfDayList.add(makeHalfDay(rs,assignTakenOff));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return halfDayList;
    }
    public static JHalfDay makeHalfDay(ResultSet rs, boolean assignTakenOff) throws SQLException {
        return HalfDay.convert(
                rs.getInt("half_day_id"),
                rs.getInt("day_of_week_id"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getBoolean("work_day"),
                rs.getBoolean("is_am"),
                assignTakenOff ? ApplicationConfig.getEmployeeRepository().findByDayOff(rs.getInt("half_day_id"),false) : null
        );
    }
}
