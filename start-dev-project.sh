#!/bin/bash

# Start Docker containers
echo "Starting Docker containers..."
docker-compose -f ./vacation-planner-pro/docker-compose.yml up -d || exit

echo "Starting Maven project with Open Liberty..."
cd ./vacation-planner-pro || exit
mvn liberty:dev