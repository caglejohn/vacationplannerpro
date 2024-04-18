package UlsterCS250.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import UlsterCS250.entities.JEmployee;
import UlsterCS250.producers.RepositoryProducer;
import UlsterCS250.requests.EmployeeVM;

public class ToFixEmployeeRepository {
    /*
    private static String dbUrl = "jdbc:postgresql://localhost:5432/auth_database";
    private static String user = "vcpp";
    private static String pass = "abc123";
    private static RepositoryProducer repositoryProducer = new RepositoryProducer();


    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());
    
    public ArrayList<JEmployee> findAll() {
        boolean assignTimeOff = true;
        ArrayList<JEmployee> employeesList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees ORDER BY employee_id");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) employeesList.add(makeEmployee(rs,assignTimeOff));
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
        LOGGER.log(Level.SEVERE, "login hit: ");
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Employees WHERE username = ? AND password_hash = ?");
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, employee.getPassword());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            LOGGER.log(Level.SEVERE, "count: ", count);
            return count == 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while attempting to log in: ", e);
            return false;
        }
    }

    public JEmployee findByUsername(String username) {
        boolean assignTimeOff = true;
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE username ILIKE ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LOGGER.info("Found employee by username: " + username);
                return makeEmployee(rs,assignTimeOff);
            } else {
                LOGGER.warning("Employee not found with username: " + username);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding employee by username: " + username, e);
            return null;
        }
    }
    public ArrayList<JEmployee> findByDayOff(int index, boolean assignTimeOff) {
        ArrayList<JEmployee> jEmployees = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees " +
                    "JOIN EmployeeTimeOffs ON Employees.id = EmployeeTimeOffs.employee_id " +
                    "WHERE EmployeeTimeOffs.half_day_id = ?");
            stmt.setInt(1,index);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) jEmployees.add(makeEmployee(rs, assignTimeOff));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while finding days", e);
            e.printStackTrace();
        }
        return jEmployees;
    }
    public static JEmployee makeEmployee(ResultSet rs, boolean assignTimeOff) throws SQLException {
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
                rs.getString("created_at"),
                rs.getString("department_id"),
                rs.getString("years_of_service"),
                assignTimeOff ? repositoryProducer.produceHalfDayRepository().findByEmployee(rs.getInt("employee_id"), false) : null
        );
    }
    public void addEmployee(String username, String password) throws SQLException {
        if (!isUsernameUnique(username)) {
            throw new SQLException("Username already exists");
        }
        try {
            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Employees (username, password_hash, email, first_name, last_name, is_manager, is_active, last_login, created_at, department_id, years_of_service) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, "test@test.com");
            stmt.setString(4, "test");
            stmt.setString(5, "test");
            stmt.setBoolean(6, false);
            stmt.setBoolean(7, true);
            stmt.setInt(8, 1);
            stmt.setInt(9, 1);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) LOGGER.info("Employee added successfully");
            else LOGGER.warning("Failed to add employee");
        }
        catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Error while adding employee", e);
        }
    }
    */
}
