#!/bin/bash

echo "Cleaning Maven project..."
cd ./vacation-planner-pro || exit
docker-compose -f ./docker-compose.yml down -v
mvn clean
if [ $? -ne 0 ]; then
    echo "Failed to clean the Maven project."
    exit 1
else
    echo "Maven project cleaned successfully."
fi
