-- Enable the 'citext' extension to allow case-insensitive text fields.
-- This command is idempotent and won cause errors if the extension
-- is already enabled in the database.
CREATE EXTENSION IF NOT EXISTS citext;

-- Create the 'users' table with various fields, using 'citext' for
-- case-insensitive columns like 'email', 'first_name', and 'last_name'.
-- The table includes a mix of data types and constraints to fit our
-- application's needs.
CREATE TABLE IF NOT EXISTS users (
     user_id SERIAL PRIMARY KEY,
     username VARCHAR(255) UNIQUE NOT NULL,
     password_hash VARCHAR(255) NOT NULL,
     email CITEXT UNIQUE NOT NULL,
     first_name CITEXT NOT NULL,
     last_name CITEXT NOT NULL,
     is_manager BOOLEAN NOT NULL DEFAULT FALSE,
     is_active BOOLEAN NOT NULL DEFAULT TRUE,
     created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
     last_login TIMESTAMP WITH TIME ZONE
);

-- Insert some sample records into the 'users' table. These records are for
-- demonstration and initial testing purposes. In the real-world scenario,
-- we'll probably have a more secure way to handle passwords and perhaps more
-- complex user creation workflows.
INSERT INTO users (username, password_hash, email, first_name, last_name, is_manager, is_active, last_login) VALUES
    ('johndoe', 'hashed_password1', 'johndoe@example.com', 'John', 'Doe', FALSE, TRUE, NULL),
    ('janedoe', 'hashed_password2', 'janedoe@example.com', 'Jane', 'Doe', TRUE, TRUE, NOW()),
    ('bobsmith', 'hashed_password3', 'bobsmith@example.com', 'Bob', 'Smith', FALSE, TRUE, NULL);
