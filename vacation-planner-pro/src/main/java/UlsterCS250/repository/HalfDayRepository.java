package UlsterCS250.repository;

import UlsterCS250.entities.JHalfDay;
import UlsterCS250.producers.RepositoryProducer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HalfDayRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static RepositoryProducer repositoryProducer = new RepositoryProducer();
    private static final Logger LOGGER = Logger.getLogger(HalfDayRepository.class.getName());

    public ArrayList<JHalfDay> findAll() {
        ArrayList<JHalfDay> halfDayList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            ResultSet rs = conn.prepareStatement("SELECT * FROM HalfDays ORDER BY half_day_id").executeQuery();
            while(rs.next()) halfDayList.add(makeHalfDay(rs));
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
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.first());
            while(rs.next()) halfDayList.add(makeHalfDay(rs));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return halfDayList;
    }
    public static JHalfDay makeHalfDay(ResultSet rs) throws SQLException {
        return new JHalfDay(
                rs.getInt("half_day_id"),
                rs.getBoolean("is_am"),
                rs.getInt("day_of_week_id"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getBoolean("is_work_day")
        );
    }
}
