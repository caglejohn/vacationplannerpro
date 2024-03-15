@echo off

REM Change to the project directory where the Open Liberty server and docker-compose.yml are located
cd .\vacation-planner-pro || exit /b 1

REM Stop the Open Liberty server
echo Stopping Open Liberty server...
call mvn liberty:stop
if %errorlevel% neq 0 goto error

cd ..

REM Stop and remove Docker containers
echo Stopping Docker containers...
docker-compose -f .\vacation-planner-pro\docker-compose.yml down
if %errorlevel% neq 0 goto error

echo Maven project and Docker containers have been successfully stopped.
goto eof

:error
echo There was an error stopping the Docker containers or the Open Liberty server.
exit /b 1

:eof
