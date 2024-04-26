-- Enables the 'citext' extension to allow case-insensitive text fields.
CREATE EXTENSION IF NOT EXISTS citext;
-- Creates the 'Employees' table with various fields
CREATE TABLE Employees (
    employee_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email CITEXT UNIQUE NOT NULL,
    first_name CITEXT NOT NULL,
    last_name CITEXT NOT NULL,
    is_manager BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    create_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    years_of_service INTEGER
);

CREATE TABLE HalfDays (
    half_day_id SERIAL PRIMARY KEY NOT NULL,
    is_am BOOLEAN NOT NULL,
    day_of_week_id INTEGER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_work_day BOOLEAN NOT NULL
);

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

CREATE TABLE UserSessions (
    session_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    employee_id INTEGER NOT NULL REFERENCES Employees(employee_id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX index_usersessions_employee_id ON UserSessions(employee_id);

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
