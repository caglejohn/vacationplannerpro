package UlsterCS250.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.owasp.encoder.Encode;

@WebServlet(urlPatterns = "/QueryAuthDbNew")
public class QueryAuthDbNew extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String dbUrl;
    private String user;
    private String pass;

    @Override
    public void init() throws ServletException {
        super.init();
        // get database connection details from environment variables
        dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
        //user = System.getenv("DB_USERNAME");
        user = "vcpp";
        // pass = System.getenv("DB_PASSWORD");
        pass = "abc123";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY employee_id")) {
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                StringBuilder usersList = new StringBuilder("<html><head><title>User List</title></head><body>");
                usersList.append("<h2>Employees List</h2>");
                usersList.append("<table border='1'><tr>");
                // Dynamically generate table headers based on column names
                for (int i = 1; i <= columnCount; i++) {
                    usersList.append("<th>").append(metaData.getColumnLabel(i)).append("</th>");
                }
                usersList.append("</tr>");

                // Fetch and display each row's data
                while (rs.next()) {
                    usersList.append("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        String columnValue = rs.getString(i);
                        // Encode all values for XSS protection
                        usersList.append("<td>").append(org.owasp.encoder.Encode.forHtml(columnValue)).append("</td>");
                    }
                    usersList.append("</tr>");
                }
                usersList.append("</table></body></html>");
                response.getWriter().append(usersList.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("Error accessing the database: ").append(e.getMessage());
        }
    }
}
