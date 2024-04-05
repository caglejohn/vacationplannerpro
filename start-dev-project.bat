@echo off

echo Starting Docker containers...
docker-compose -f .\vacation-planner-pro\docker-compose.yml up -d
if %errorlevel% neq 0 goto :error

echo Starting Maven project with Open Liberty...
cd .\vacation-planner-pro || exit /b
mvn liberty:dev
goto :eof

:error
echo Failed to start the Docker containers.
exit /b 1