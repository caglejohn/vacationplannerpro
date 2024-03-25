package UlsterCS250.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getAuthConn(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/auth_database", "vcpp", "abc123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
