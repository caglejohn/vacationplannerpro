package UlsterCS250.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/QueryAuthDb")
public class QueryAuthDb extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String dbUrl;
    private String user;
    private String pass;

    @Override
    public void init() throws ServletException {
        super.init();
        // Retrieve database connection details from environment variables
        // dbUrl = "jdbc:postgresql://localhost:5432/" + System.getenv("auth_database");
        dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
        //user = System.getenv("DB_USERNAME");
        user = "vcpp";
        // pass = System.getenv("DB_PASSWORD");
        pass = "abc123";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT username FROM users ORDER BY user_id")) {
            try (ResultSet rs = stmt.executeQuery()) {
                StringBuilder usersList = new StringBuilder("Usernames:\n");
                while (rs.next()) {
                    usersList.append(rs.getString("username")).append("\n");
                }
                response.getWriter().append(usersList.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("Error accessing the database: ").append(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
