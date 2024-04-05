package UlsterCS250.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;


import UlsterCS250.entities.VacationProfile;

public class VacationProfileRepository {

     private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public ArrayList<VacationProfile> findAll() {
    ArrayList<VacationProfile> profile = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VacationProfiles ORDER BY id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()){
                        VacationProfile pro = new VacationProfile(
                        rs.getLong("employee_id"),
                        rs.getLong("total_vacation_days"),
                        rs.getLong("personal_choice_days"),
                        rs.getLong("vacation_days_taken"),
                        rs.getLong("vacation_days_remaining"),
                        rs.getLong("personal_choice_taken"),
                        rs.getLong("personal_choice_days_remaining")
                        );
                        profile.add(pro);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employees", e);
            e.printStackTrace();
        }
        return profile;
    }
}
