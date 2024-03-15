#!/bin/bash

# Assuming the script is executed from the directory containing the vacation-planner-pro directory

# Navigate to the Maven project directory to stop the Open Liberty server
echo "Navigating to the Maven project directory..."
cd ./vacation-planner-pro || exit

echo "Stopping the Maven project running with Open Liberty..."
mvn liberty:stop || {
    echo "Failed to stop the Open Liberty server. Ensure it's running and you're in the correct directory."
    exit 1
}

# Navigate back to the parent directory where docker-compose.yml is expected to be found
cd ..

echo "Stopping Docker containers..."
docker-compose -f ./vacation-planner-pro/docker-compose.yml down || {
    echo "Failed to stop Docker containers."
    exit 1
}

echo "Maven project and Docker containers have been successfully stopped."
