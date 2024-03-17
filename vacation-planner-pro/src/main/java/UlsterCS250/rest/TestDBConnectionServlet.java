package UlsterCS250.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/TestDBConnection")
public class TestDBConnectionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String dbUrl;
    private String user;
    private String pass;

    @Override
    public void init() throws ServletException {
        super.init();
        // Simplify initialization by hardcoding values for testing
        dbUrl = "jdbc:postgresql://localhost:5432/testdata"; // Use your DB1_NAME directly for testing
        user = "postgres"; // Use your DB_USERNAME directly
        pass = "abc123"; // Use your DB_PASSWORD directly
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass)) {
            // If this point is reached, the database connection was successful
            response.getWriter().append("Database connection successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("Error connecting to the database: ").append(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
