package UlsterCS250.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.entities.JHalfDay;
import UlsterCS250.responses.CalendarDay;

public class VacationDayRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(VacationDayRepository.class.getName());

    public List<CalendarDay> getDays() {
        List<CalendarDay> days = new ArrayList<>();
        int calIndex = 0;

        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT start_date FROM HalfDays")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("start_date");
                List<JHalfDay> day = new ArrayList<>();
                day.add(getHalfDay(date, true));
                day.add(getHalfDay(date, false));
                CalendarDay newDay = new CalendarDay(calIndex, date, day);
                days.add(newDay);
                calIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return days;
    }

    private JHalfDay getHalfDay(Date date, boolean isAM) {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT half_day_id, is_am, day_of_week_id, start_date, end_date, is_work_day FROM HalfDays WHERE start_date = ? AND is_am = ?")) {
            stmt.setDate(1, date);
            stmt.setBoolean(2, isAM);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int halfDayId = rs.getInt("half_day_id");
                boolean isAMHalf = rs.getBoolean("is_am");
                int dayOfWeekId = rs.getInt("day_of_week_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                boolean isWorkDay = rs.getBoolean("is_work_day");

                ArrayList<JEmployeeTimeOff> employeeTimeOffs = getEmployeeTimeOffs(halfDayId);
                return new JHalfDay(halfDayId, isAMHalf, dayOfWeekId, startDate, endDate, isWorkDay, employeeTimeOffs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<JEmployeeTimeOff> getEmployeeTimeOffs(int halfDayId) {
        ArrayList<JEmployeeTimeOff> employeeTimeOffs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT employee_time_off_id, employee_id, half_day_id, reason FROM EmployeeTimeOffs WHERE half_day_id = ?")) {
            stmt.setInt(1, halfDayId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int timeOffId = rs.getInt("employee_time_off_id");
                int employeeId = rs.getInt("employee_id");
                int nhalfDayId = rs.getInt("half_day_id");
                String reason = rs.getString("reason");
                employeeTimeOffs.add(new JEmployeeTimeOff(timeOffId, employeeId, nhalfDayId, reason));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeTimeOffs;
    }

    public List<CalendarDay> getDaysForMonthAndYear(int month, int year) {
        List<CalendarDay> days = new ArrayList<>();
        int calIndex = 0;

        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT DISTINCT start_date FROM HalfDays WHERE EXTRACT(MONTH FROM start_date) = ? AND EXTRACT(YEAR FROM start_date) = ?")) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("start_date");
                List<JHalfDay> halfDays = new ArrayList<>();
                halfDays.add(getHalfDay(date, true));
                halfDays.add(getHalfDay(date, false));
                CalendarDay newDay = new CalendarDay(calIndex, date, halfDays);
                days.add(newDay);
                calIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return days;
    }
}
