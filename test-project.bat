@echo off

echo Cleaning and compiling Maven project...
cd .\vacation-planner-pro || exit /b
mvn clean compile

if %errorlevel% neq 0 (
    echo Failed to clean and compile the Maven project.
    exit /b 1
) else (
    echo Maven project cleaned and compiled successfully.
)
