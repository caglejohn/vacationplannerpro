-- Enables the 'citext' extension to allow case-insensitive text fields.
CREATE EXTENSION IF NOT EXISTS citext;

-- Creates the 'Departments' table to store department information.
-- This table includes department IDs and names.
CREATE TABLE IF NOT EXISTS Departments (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL
);

-- Departments Dummy Data
INSERT INTO Departments (department_name) VALUES
    ('Human Resources'),
    ('Engineering'),
    ('Marketing');

-- Creates the 'Employees' table with various fields
CREATE TABLE IF NOT EXISTS Employees (
    employee_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    first_name CITEXT NOT NULL,
    last_name CITEXT NOT NULL,
    is_manager BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP WITH TIME ZONE,
    department_id INTEGER,
    years_of_service INTEGER,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id) ON DELETE SET NULL
);

-- Employees Dummy Data
INSERT INTO Employees (
    username,
    password_hash,
    email,
    first_name,
    last_name,
    is_manager,
    is_active,
    last_login,
    department_id,
    years_of_service
) VALUES
      ('johndoe', 'hashed_password1', 'johndoe@example.com', 'John', 'Doe', FALSE, TRUE, NULL, 1, 5),
      ('janedoe', 'hashed_password2', 'janedoe@example.com', 'Jane', 'Doe', TRUE, TRUE, NOW(), 2, 8),
      ('bobsmith', 'hashed_password3', 'bobsmith@example.com', 'Bob', 'Smith', FALSE, TRUE, NULL, 3, 3),
      ('alicewhite', 'hashed_password4', 'alicewhite@example.com', 'Alice', 'White', FALSE, TRUE, NOW(), 1, 2),
      ('tomblack', 'hashed_password5', 'tomblack@example.com', 'Tom', 'Black', TRUE, FALSE, NULL, 2, 10),
      ('sarahgreen', 'hashed_password6', 'sarahgreen@example.com', 'Sarah', 'Green', FALSE, TRUE, NOW(), 3, 4),
      ('davidbrown', 'hashed_password7', 'davidbrown@example.com', 'David', 'Brown', FALSE, TRUE, NULL, 1, 1),
      ('emilyclark', 'hashed_password8', 'emilyclark@example.com', 'Emily', 'Clark', TRUE, TRUE, NOW(), 2, 7),
      ('brianmiller', 'hashed_password9', 'brianmiller@example.com', 'Brian', 'Miller', FALSE, TRUE, NULL, 3, 5),
      ('chloewilson', 'hashed_password10', 'chloewilson@example.com', 'Chloe', 'Wilson', TRUE, TRUE, NOW(), 1, 6);

-- Note: The FOREIGN KEY constraint on 'department_id' in the 'users' table is set to ON DELETE SET NULL.
-- So, if a department is deleted, the 'department_id' in the associated user records will be set to NULL.

-------------------------------------------------------------------------------------------

-- VacationProfiles Table: Tracks individual vacation accruals and usage for each employee.
CREATE TABLE IF NOT EXISTS VacationProfiles (
    vacation_profile_id SERIAL PRIMARY KEY,
    employee_id INTEGER, -- References the unique employee ID
    total_vacation_days INTEGER NOT NULL, -- Total allocated vacation days
    personal_choice_days INTEGER NOT NULL, -- Total allocated personal choice days
    vacation_days_taken INTEGER NOT NULL, -- Total vacation days taken
    vacation_days_remaining INTEGER NOT NULL, -- Total remaining vacation days
    personal_choice_taken INTEGER NOT NULL, -- Personal choice days taken
    personal_choice_days_remaining INTEGER NOT NULL, -- Total remaining personal choice days
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) ON DELETE CASCADE -- Cascading delete with employee deletion
);

-- VacationProfiles Dummy Data
INSERT INTO VacationProfiles (employee_id, total_vacation_days, personal_choice_days, vacation_days_taken, vacation_days_remaining, personal_choice_taken, personal_choice_days_remaining) VALUES
    (1, 22, 3, 5, 17, 2, 1), -- More personal choice days used
    (2, 18, 5, 0, 18, 0, 5), -- Hasn't taken any days yet
    (3, 20, 2, 10, 10, 1, 1), -- Half of the vacation days already used
    (4, 25, 5, 3, 22, 0, 5), -- Minimal vacation days taken
    (5, 15, 5, 7, 8, 4, 1), -- Most personal choice days used
    (6, 30, 10, 15, 15, 5, 5); -- High entitlement, half used


-- VacationRequests Dummy Data
INSERT INTO VacationRequests (approver_id, time_off_id, reason, status) VALUES
    -- Employee 1's approved vacation for a family vacation
    (3, 1, 'Family vacation', 'Approved'),

    -- Employee 2's approved half-day PM for a medical appointment
    (1, 2, 'Medical appointment', 'Approved'),

    -- Employee 3's pending full-day vacation request for attending a wedding
    (2, 3, 'Attending a wedding', 'Pending'),

    -- Employee 4's approved personal choice day off
    (3, 4, 'Personal day off', 'Approved'),

    -- Employee 5's declined full-day vacation request for a summer trip
    (1, 5, 'Summer trip', 'Declined'),

    -- Employee 6's approved half-day AM for a dentist appointment
    (2, 6, 'Dentist appointment', 'Approved'),

    -- Employee 1's pending personal choice request for a long weekend getaway
    (3, 7, 'Long weekend getaway', 'Pending'),

    -- Employee 6's approved full-day vacation for a family event
    (1, 8, 'Family event', 'Approved');


-- DepartmentVacationsView: Provides an aggregated view of vacation requests by department.
CREATE VIEW DepartmentVacationsView AS
SELECT
    d.department_name,
    e.employee_id,
    vr.start_date,
    vr.end_date,
    vr.type,
    vr.status
FROM Departments d
    JOIN Employees e ON d.department_id = e.department_id
    JOIN VacationRequests vr ON e.employee_id = vr.employee_id;

-- EmployeeTimeOffs Table: Tracks employee time offs, allowing for half-day (AM/PM) and full-day tracking.
CREATE TABLE IF NOT EXISTS EmployeeTimeOffs (
    employee_time_off_id SERIAL PRIMARY KEY, -- Unique identifier for each time off record
    employee_id INTEGER NOT NULL, -- References the unique employee ID
    half_day_id INTEGER NOT NULL,
    is_personal BOOLEAN DEFAULT FALSE, -- Indicates if the time off is personal choice
    approver_id INTEGER, -- Employee ID of the approver
    reason VARCHAR(255), -- Reason for the request
    status VARCHAR(255) NOT NULL, -- Status of the request (e.g., approved, pending, declined)
    FOREIGN KEY (approver_id) REFERENCES Employees(employee_id) ON DELETE SET NULL -- Approver deletion sets field to NULL
    FOREIGN KEY (employee_time_off_id) REFERENCES EmployeeTimeOffs(employee_time_off_id)
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) ON DELETE CASCADE -- Cascading delete with employee deletion
    FOREIGN KEY (half_day_id) REFERENCES HalfDays(half_day_id) ON DELETE CASCADE -- Cascading delete with day deletion
);

-- EmployeeTimeOffs Dummy Data
INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, is_personal) VALUES
    (1, 0, FALSE),
    (1, 0, FALSE),
    (2, 1, FALSE),
    (4, 1, TRUE), -- Personal choice day
    (6, 1, FALSE),
    (6, 1, FALSE),
    (6, 0, FALSE),
    (1, 0, TRUE); -- Another example of personal choice day

CREATE TABLE IF NOT EXISTS HalfDays (
    half_day_id INTEGER PRIMARY KEY NOT NULL, --index of where this day is located
    is_am BOOLEAN NOT NULL, -- what part of the day this half day is
    day_of_week_id INTEGER NOT NULL, -- the day of the week this half day is in
    start_date DATE NOT NULL, -- start date
    end_date DATE NOT NULL, -- end date
    work_day BOOLEAN NOT NULL -- whether or not company works this day
);
INSERT INTO HalfDays (ind, is_am, day_of_week_id, start_date, end_date, work_day) VALUES
    (0, TRUE, 1, '2024-01-01', '2024-01-01', TRUE),
    (1, FALSE, 1, '2024-01-01', '2024-01-02', TRUE);
------------------------------------------------------------------------------------

-- Audit Logs Table: Stores user actions for audit purposes.
CREATE TABLE IF NOT EXISTS audit_logs (
    audit_log_id SERIAL PRIMARY KEY, -- Unique audit log entry identifier
    user_id INTEGER NOT NULL, -- User ID (reference handled at application level)
    action VARCHAR(255) NOT NULL, -- Description of the action performed
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL, -- Time when the action was performed
    details JSON -- Additional action details in JSON format
);

-- Index for optimizing queries by user ID
CREATE INDEX idx_audit_logs_user_id ON audit_logs(user_id);

-- Index for optimizing queries by timestamp
CREATE INDEX idx_audit_logs_timestamp ON audit_logs(timestamp);

-- audit_logs Dummy Data
INSERT INTO audit_logs (user_id, action, timestamp, details) VALUES
    (1, 'login', '2023-03-20 08:00:00+00', '{"ip": "192.168.1.1", "status": "success"}'),
    (2, 'update_profile', '2023-03-20 09:15:00+00', '{"field": "email", "old_value": "old@example.com", "new_value": "new@example.com"}'),
    (1, 'logout', '2023-03-20 10:30:00+00', '{}'),
    (3, 'delete_item', '2023-03-20 11:00:00+00', '{"item_id": 123, "item_type": "document"}'),
    (2, 'login', '2023-03-21 08:05:00+00', '{"ip": "192.168.1.2", "status": "success"}'),
    (3, 'login', '2023-03-21 08:20:00+00', '{"ip": "192.168.1.3", "status": "failed", "reason": "wrong password"}'),
    (1, 'create_item', '2023-03-21 09:00:00+00', '{"item_id": 124, "item_type": "document", "action": "create"}'),
    (2, 'logout', '2023-03-21 17:00:00+00', '{}'),
    (3, 'login', '2023-03-22 08:10:00+00', '{"ip": "192.168.1.4", "status": "success"}'),
    (1, 'update_profile', '2023-03-22 09:20:00+00', '{"field": "password", "status": "changed"}');

-- Note: The JSON details are represented as strings in these examples.
-- So we need to make sure that the actual implementation properly escapes any special characters as needed.
