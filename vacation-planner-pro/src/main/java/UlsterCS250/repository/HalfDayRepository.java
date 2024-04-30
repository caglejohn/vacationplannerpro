package UlsterCS250.repository;

import UlsterCS250.entities.JHalfDay;
import UlsterCS250.planner.Calendar;
import UlsterCS250.planner.HalfDay;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HalfDayRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(HalfDayRepository.class.getName());

    public ArrayList<JHalfDay> findAll() {
        ArrayList<JHalfDay> halfDayList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            ResultSet rs = conn.prepareStatement("SELECT * FROM HalfDays ORDER BY half_day_id").executeQuery();
            while (rs.next())
                halfDayList.add(makeHalfDay(rs));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return halfDayList;
    }

    public ArrayList<JHalfDay> findByEmployee(int id) {
        ArrayList<JHalfDay> halfDayList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HalfDays " +
                    "JOIN EmployeeTimeOffs ON EmployeeTimeOffs.half_day_id = HalfDays.half_day_id " +
                    "WHERE EmployeeTimeOffs.employee_id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.first());
            while (rs.next())
                halfDayList.add(makeHalfDay(rs));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return halfDayList;
    }

    public void fillCalendar() {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HalfDays");
            if (stmt.executeQuery().next())
                return;
            stmt = conn.prepareStatement(
                    "INSERT INTO HalfDays (half_day_id, is_am, day_of_week_id, start_date, end_date, is_work_day) VALUES (?, ?, ?, ?, ?, ?)");
            Calendar calendar = new Calendar(TimeZone.getTimeZone("EST"));
            calendar.setWeeklyWorkPattern(0, calendar.getCalendar().size() - 1, new boolean[] { false, false, true,
                    true, true, true, true, true, true, true, true, true, false, false });
            for (HalfDay day : calendar.getCalendar()) {
                JHalfDay jHalfDay = day.convert();
                stmt.setInt(1, jHalfDay.getId());
                stmt.setBoolean(2, jHalfDay.isAm());
                stmt.setInt(3, jHalfDay.getDayOfWeekId());
                stmt.setDate(4, jHalfDay.getStartDate());
                stmt.setDate(5, jHalfDay.getEndDate());
                stmt.setBoolean(6, jHalfDay.isWorkDay());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static JHalfDay makeHalfDay(ResultSet rs) throws SQLException {
        return new JHalfDay(
                rs.getInt("half_day_id"),
                rs.getBoolean("is_am"),
                rs.getInt("day_of_week_id"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getBoolean("is_work_day"));
    }

    public int findHalfDayIdByDateAndTime(LocalDate startDate, boolean isAm) {
        int halfDayId = 0;
        LOGGER.warning("hit");
        LOGGER.log(Level.SEVERE, startDate.toString());
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);

            PreparedStatement stmt = conn
                    .prepareStatement("SELECT half_day_id FROM HalfDays WHERE start_date = ? AND is_am = ?");

            Date sqlDate = java.sql.Date.valueOf(startDate);
            stmt.setDate(1, sqlDate);
            stmt.setBoolean(2, isAm);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                halfDayId = rs.getInt("half_day_id");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding half day by date and time", e);
            e.printStackTrace();
        }

        return halfDayId;
    }
}
