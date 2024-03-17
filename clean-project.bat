@echo off

echo Cleaning Maven project...
cd .\vacation-planner-pro || exit /b
mvn clean

if %errorlevel% neq 0 (
    echo Failed to clean the Maven project.
    exit /b 1
) else (
    echo Maven project cleaned successfully.
)
