-- Enables the 'citext' extension to allow case-insensitive text fields.
CREATE EXTENSION IF NOT EXISTS citext;
-- Creates the 'Employees' table with various fields
CREATE TABLE Employees (
    employee_id INTEGER PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    first_name CITEXT NOT NULL,
    last_name CITEXT NOT NULL,
    is_manager BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    create_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP WITH TIME ZONE,
    years_of_service INTEGER
);
-- Employees Dummy Data
INSERT INTO Employees (employee_id, username, password_hash, email, first_name, last_name, is_manager, is_active, create_time, last_login, years_of_service) VALUES
    (0, 'johndoe', 'hashed_password1', 'johndoe@example.com', 'John', 'Doe', FALSE, TRUE, NOW(), NULL, 5),
    (1, 'janedoe', 'hashed_password2', 'janedoe@example.com', 'Jane', 'Doe', TRUE, TRUE, NOW(), NULL, 8),
    (2, 'bobsmith', 'hashed_password3', 'bobsmith@example.com', 'Bob', 'Smith', FALSE, TRUE, NOW(), NULL, 3),
    (3, 'alicewhite', 'hashed_password4', 'alicewhite@example.com', 'Alice', 'White', FALSE, TRUE, NOW(), NULL, 2),
    (4, 'tomblack', 'hashed_password5', 'tomblack@example.com', 'Tom', 'Black', TRUE, FALSE, NOW(), NULL, 10),
    (5, 'sarahgreen', 'hashed_password6', 'sarahgreen@example.com', 'Sarah', 'Green', FALSE, TRUE, NOW(), NULL, 4),
    (6, 'davidbrown', 'hashed_password7', 'davidbrown@example.com', 'David', 'Brown', FALSE, TRUE, NOW(), NULL, 1),
    (7, 'emilyclark', 'hashed_password8', 'emilyclark@example.com', 'Emily', 'Clark', TRUE, TRUE, NOW(), NULL, 7),
    (8, 'brianmiller', 'hashed_password9', 'brianmiller@example.com', 'Brian', 'Miller', FALSE, TRUE, NOW(), NULL, 5),
    (9, 'chloewilson', 'hashed_password10', 'chloewilson@example.com', 'Chloe', 'Wilson', TRUE, TRUE, NOW(), NULL, 6);

-- HalfDay table
CREATE TABLE HalfDays (
    half_day_id INTEGER PRIMARY KEY NOT NULL, --index of where this day is located
    is_am BOOLEAN NOT NULL, -- what part of the day this half day is
    day_of_week_id INTEGER NOT NULL, -- the day of the week this half day is (Sunday=0, Monday=1...)
    start_date DATE NOT NULL, -- start date
    end_date DATE NOT NULL, -- end date
    is_work_day BOOLEAN NOT NULL -- whether or not company works this day
    );
INSERT INTO HalfDays (half_day_id, is_am, day_of_week_id, start_date, end_date, is_work_day) VALUES
    (0, TRUE, 1, '2024-01-01', '2024-01-01', TRUE),
    (1, FALSE, 1, '2024-01-01', '2024-01-02', TRUE);
------------------------------------------------------------------------------------

-- EmployeeTimeOffs Table: Tracks employee time offs, allowing for half-day (AM/PM) and full-day tracking.
CREATE TABLE EmployeeTimeOffs (
    employee_time_off_id SERIAL PRIMARY KEY, -- Unique identifier for each time off record
    employee_id INTEGER NOT NULL, -- References the unique employee ID
    half_day_id INTEGER NOT NULL,
    reason VARCHAR(255), -- Reason for the request
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) ON DELETE CASCADE, -- Cascading delete with employee deletion
    FOREIGN KEY (half_day_id) REFERENCES HalfDays(half_day_id) ON DELETE CASCADE -- Cascading delete with day deletion
    );

-- EmployeeTimeOffs Dummy Data
INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, reason) VALUES
    (0, 0, 'Family vacation'),
    (0, 1, 'Medical appointment');

-- VacationProfiles Table: Tracks individual vacation accruals and usage for each employee.
CREATE TABLE VacationProfiles (
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

-- Audit Logs Table: Stores user actions for audit purposes.
-- CREATE TABLE audit_logs (
--    audit_log_id SERIAL PRIMARY KEY, -- Unique audit log entry identifier
--    user_id INTEGER NOT NULL, -- User ID (reference handled at application level)
--    action VARCHAR(255) NOT NULL, -- Description of the action performed
--    timestamp TIMESTAMP WITH TIME ZONE NOT NULL, -- Time when the action was performed
--    details JSON -- Additional action details in JSON format
--    );

-- Index for optimizing queries by user ID
-- CREATE INDEX idx_audit_logs_user_id ON audit_logs(user_id);

-- Index for optimizing queries by timestamp
-- CREATE INDEX idx_audit_logs_timestamp ON audit_logs(timestamp);

-- audit_logs Dummy Data
-- INSERT INTO audit_logs (user_id, action, timestamp, details) VALUES
--    (1, 'login', '2023-03-20 08:00:00+00', '{"ip": "192.168.1.1", "status": "success"}'),
--    (2, 'update_profile', '2023-03-20 09:15:00+00', '{"field": "email", "old_value": "old@example.com", "new_value": "new@example.com"}'),
--    (1, 'logout', '2023-03-20 10:30:00+00', '{}'),
--    (3, 'delete_item', '2023-03-20 11:00:00+00', '{"item_id": 123, "item_type": "document"}'),
--    (2, 'login', '2023-03-21 08:05:00+00', '{"ip": "192.168.1.2", "status": "success"}'),
--    (3, 'login', '2023-03-21 08:20:00+00', '{"ip": "192.168.1.3", "status": "failed", "reason": "wrong password"}'),
--    (1, 'create_item', '2023-03-21 09:00:00+00', '{"item_id": 124, "item_type": "document", "action": "create"}'),
--    (2, 'logout', '2023-03-21 17:00:00+00', '{}'),
--    (3, 'login', '2023-03-22 08:10:00+00', '{"ip": "192.168.1.4", "status": "success"}'),
--    (1, 'update_profile', '2023-03-22 09:20:00+00', '{"field": "password", "status": "changed"}');

-- Note: The JSON details are represented as strings in these examples.
-- So we need to make sure that the actual implementation properly escapes any special characters as needed.
