# Vacation Planner Pro

## Overview

Vacation Planner Pro is a web-based application designed for large organizations, aiming to simplify the complexities of vacation planning and tracking. This solution offers a standardized platform for employees and managers to create, monitor, and manage vacation times effectively. It provides a detailed organizational view of vacation accruals, usages, and schedules, ensuring a smooth and efficient vacation planning process.

## Features

- **Vacation Profiles:** Enables the creation and management of individual vacation profiles for employees, factoring in total vacation based on years of service and personal choice holidays.
- **Vacation Planning:** Allows employees to submit their vacation plans for the year, distinguishing between full or half days for standard vacations and personal choice holidays.
- **Accrual Tracking:** Implements a comprehensive system to track accrued, taken, and remaining vacation days for each employee, ensuring accurate vacation management.
- **Quarterly Summaries:** Provides automated alerts and summaries for both employees and managers regarding vacation times accrued but not taken, promoting proactive vacation usage.
- **Departmental Views:** Facilitates the visualization of department-wide vacation schedules to aid in planning and coordination among team members.
- **End-of-Year Reporting:** Features the ability to carry over and report on vacations not taken at the end of the year, aiding in future vacation planning.

## Technical Stack

- **Backend:** Utilizes Java-based services, hosted on IBM's Open Liberty application server, ensuring high performance and reliability.
- **Frontend:** Employs flexible frontend development practices using preferred JavaScript libraries, allowing for a dynamic user experience.
- **Security:** Secured with JSON Web Tokens (JWTs) for robust authentication and authorization measures.
- **Microservices Architecture:** Designed for scalability and resilience, adhering to best practices in microservices architecture.

## Usage

- **Employees:** Can log in to the planner, view and update their vacation plans, and observe other department members' vacation schedules.
- **Managers:** Possess all the capabilities of employees, in addition to accessing departmental summaries and alerts on vacations accrued but not taken.

## Connecting to this repository
1. Fork this repository
   - button on the top right of this repo
2. Create a new project in an IDE of your choice. (I use IntelliJ)
    - Intellij link: [jetbrains.com]()
3. Clone this repository using Git
    - Install Git at [git-scm.com]()
    - run command "git clone https://github.com/xxMYFORKEDADDRESSxx"

## Running the Application
1. Navigate to the directory with the pom.xml file
    - run command "cd vacation-planner-pro" on windows
2. Build using Maven
    - Install Maven at [https://maven.apache.org/download.cgi]() (Binary zip archive for windows)
    - Create a system path variable for Maven pointed at its "bin" folder
    - run command "mvn liberty:run"
  
## License

To be discussed!
<!--This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.-->

## Acknowledgments

- A special thank you to the SUNY Ulster Faculty and IBM Team for the opprotunity to work on this project.
- A heartfelt thanks to all contributors who have helped shape Vacation Planner Pro, good luck to us all!

