package UlsterCS250.util;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import UlsterCS250.entities.JEmployee;
import UlsterCS250.entities.JEmployeeTimeOff;
import UlsterCS250.repository.EmployeeRepository;
import UlsterCS250.repository.EmployeeTimeOffRepository;
import UlsterCS250.repository.HalfDayRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
public class SeedDb {

    @PostConstruct
    public void init() {
        try {
            waitForDatabase();

            HalfDayRepository h = new HalfDayRepository();
            h.fillCalendar();
            EmployeeRepository e = new EmployeeRepository();
            e.addEmployee(new JEmployee(1, "johndoe", "hashed_password1", "johndoe@example.com", "John", "Doe", false,
                    true, null, null, 5));
            e.addEmployee(new JEmployee(2, "janedoe", "hashed_password2", "janedoe@example.com", "Jane", "Doe", true,
                    true, null, null, 8));
            EmployeeTimeOffRepository o = new EmployeeTimeOffRepository();
            o.addDayOff(new JEmployeeTimeOff(1, 1, 1, "sick"));
            o.addDayOff(new JEmployeeTimeOff(2, 2, 3, "dentist"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitForDatabase() throws InterruptedException {
        while (!isDatabaseAvailable()) {
            System.out.println("waiting for db");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("db available");
    }

    private boolean isDatabaseAvailable() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/auth_database",
                "vcpp", "abc123")) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}