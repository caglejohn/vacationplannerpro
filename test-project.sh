#!/bin/bash

echo "Cleaning and compiling Maven project..."
cd ./vacation-planner-pro || exit

mvn clean compile
if [ $? -ne 0 ]; then
    echo "Failed to clean and compile the Maven project."
    exit 1
else
    echo "Maven project cleaned and compiled successfully."
fi
