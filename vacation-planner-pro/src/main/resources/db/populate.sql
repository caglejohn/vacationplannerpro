-- Employees Dummy Data
INSERT INTO Employees (username, password_hash, email, first_name, last_name, is_manager, is_active, create_time, last_login, years_of_service) VALUES
    ('johndoe', 'hashed_password1', 'johndoe@example.com', 'John', 'Doe', FALSE, TRUE, NOW(), NULL, 5),
    ('janedoe', 'hashed_password2', 'janedoe@example.com', 'Jane', 'Doe', TRUE, TRUE, NOW(), NULL, 8),
    ('bobsmith', 'hashed_password3', 'bobsmith@example.com', 'Bob', 'Smith', FALSE, TRUE, NOW(), NULL, 3),
    ('alicewhite', 'hashed_password4', 'alicewhite@example.com', 'Alice', 'White', FALSE, TRUE, NOW(), NULL, 2),
    ('tomblack', 'hashed_password5', 'tomblack@example.com', 'Tom', 'Black', TRUE, FALSE, NOW(), NULL, 10),
    ('sarahgreen', 'hashed_password6', 'sarahgreen@example.com', 'Sarah', 'Green', FALSE, TRUE, NOW(), NULL, 4),
    ('davidbrown', 'hashed_password7', 'davidbrown@example.com', 'David', 'Brown', FALSE, TRUE, NOW(), NULL, 1),
    ('emilyclark', 'hashed_password8', 'emilyclark@example.com', 'Emily', 'Clark', TRUE, TRUE, NOW(), NULL, 7),
    ('brianmiller', 'hashed_password9', 'brianmiller@example.com', 'Brian', 'Miller', FALSE, TRUE, NOW(), NULL, 5),
    ('chloewilson', 'hashed_password10', 'chloewilson@example.com', 'Chloe', 'Wilson', TRUE, TRUE, NOW(), NULL, 6);

-- EmployeeTimeOff Dummy Data
WITH inserted_employees AS (
    SELECT employee_id
    FROM Employees
    WHERE username IN ('johndoe') -- Adjust usernames as needed
)
INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, reason)
SELECT employee_id, 1, 'Family vacation' FROM inserted_employees;

WITH inserted_employees AS (
    SELECT employee_id
    FROM Employees
    WHERE username IN ('janedoe') -- Adjust usernames as needed
)
INSERT INTO EmployeeTimeOffs (employee_id, half_day_id, reason)
SELECT employee_id, 2, 'Medical appointment' FROM inserted_employees;

-- VacationProfiles dummy data
INSERT INTO VacationProfiles (employee_id, total_vacation_days, personal_choice_days, vacation_days_taken, vacation_days_remaining, personal_choice_taken, personal_choice_days_remaining) VALUES
    (1, 22, 3, 5, 17, 2, 1), -- More personal choice days used
    (2, 18, 5, 0, 18, 0, 5), -- Hasn't taken any days yet
    (3, 20, 2, 10, 10, 1, 1), -- Half of the vacation days already used
    (4, 25, 5, 3, 22, 0, 5), -- Minimal vacation days taken
    (5, 15, 5, 7, 8, 4, 1), -- Most personal choice days used
    (6, 30, 10, 15, 15, 5, 5); -- High entitlement, half used