package UlsterCS250.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.*;
import UlsterCS250.entities.JEmployee;
import UlsterCS250.requests.EmployeeVM;

public class EmployeeRepository {
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";

    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    public ArrayList<JEmployee> findAll() {
        ArrayList<JEmployee> employeesList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY employee_id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                employeesList.add(makeEmployee(rs));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employees", e);
            e.printStackTrace();
        }
        return employeesList;
    }

    public boolean isUsernameUnique(String username) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Employees WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);
                return count == 0;
            }
        }
    }

    public boolean addSession(EmployeeVM employee) throws SQLException {
        boolean isValidLogin = validateLogin(employee);

        if (isValidLogin) {
            JEmployee emp = findByUsername(employee.getUsername());
            int userId = emp.getId();

            try {
                Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO usersessions (employee_id, created_at) VALUES (?, NOW())");
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error while attempting to add user session: ", e);
                return false;
            }
        }
        return isValidLogin;
    }

    public boolean deleteSession(String sessionId) throws SQLException {
        boolean isDeleted = true;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM usersessions WHERE session_id = ?");
            stmt.setObject(1, UUID.fromString(sessionId));
            int rowDeleted = stmt.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to delete session: ", e);
            isDeleted = false;
        }
        return isDeleted;
    }

    public boolean validateSession(String sessionId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersessions WHERE session_id = ?")) {
            stmt.setObject(1, UUID.fromString(sessionId));
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to get session: ", e);
            return false;
        }
    }

    public int getEmpIdBySessionId(String sessionId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, user, pass);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersessions WHERE session_id = ?")) {
            stmt.setObject(1, UUID.fromString(sessionId));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("employee_id");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to get session: ", e);
        }
        return 0;
    }

    public String getSessionIdByEmpId(EmployeeVM employee) throws SQLException {
        JEmployee emp = findByUsername(employee.getUsername());
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT session_id FROM usersessions WHERE employee_id = ? ORDER BY created_at DESC LIMIT 1");
            stmt.setInt(1, emp.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("session_id");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to log in: ", e);
        }
        return "";
    }

    public boolean validateLogin(EmployeeVM employee) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT COUNT(*) FROM Employees WHERE username = ? AND password_hash = ?");
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, employee.getPassword());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count == 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to log in: ", e);
            return false;
        }
    }

    public JEmployee findByUsername(String username) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LOGGER.info("Found employee by username: " + username);
                return makeEmployee(rs);
            } else {
                LOGGER.warning("Employee not found with username: " + username);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee by username: " + username, e);
            return null;
        }
    }

    /*
     * public ArrayList<Employee> findByDayOff(int index) {
     * 
     * }
     */

    public static JEmployee makeEmployee(ResultSet rs) throws SQLException {
        return new JEmployee(
                rs.getInt("employee_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getBoolean("is_manager"),
                rs.getBoolean("is_active"),
                rs.getString("last_login"),
                rs.getString("create_time"),
                rs.getInt("years_of_service"));
    }

    public void addEmployee(EmployeeVM employee) throws SQLException {
        if (!isUsernameUnique(employee.getUsername())) {
            throw new SQLException("Username already exists");
        }
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Employees (username, password_hash, email, first_name, last_name, is_manager, is_active, last_login, create_time, years_of_service) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?)");
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, employee.getPassword());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, "test");
            stmt.setString(5, "test");
            stmt.setBoolean(6, false);
            stmt.setBoolean(7, true);
            stmt.setInt(8, 1);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0)
                LOGGER.info("Employee added successfully");
            else
                LOGGER.warning("Failed to add employee");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while adding employee", e);
        }
    }
}
