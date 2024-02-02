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

## Getting Started

### Prerequisites

- IBM Open Liberty application server.
- Java Development Kit (JDK) - Version 11 or later.
- Node.js and npm (for frontend development).

### Installation

1. Clone the repository:

   git clone https://github.com/caglejohn/vacationplannerpro.git

2. Navigate to the project directory and install dependencies:

   - For the backend:
     cd backend
     ./gradlew build
     
   - For the frontend:
     cd frontend
     npm install

### Running the Application

- To run the backend:
  ./gradlew libertyRun

- To run the frontend:
  npm start

## Usage

- **Employees:** Can log in to the planner, view and update their vacation plans, and observe other department members' vacation schedules.
- **Managers:** Possess all the capabilities of employees, in addition to accessing departmental summaries and alerts on vacations accrued but not taken.

## Contributing

We welcome contributions! Please consult our [CONTRIBUTING.md](CONTRIBUTING.md) document for details on how to submit pull requests and participate in the development process.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- A special thank you to the IBM Open Liberty Team for providing the robust application server.
- Heartfelt thanks to all contributors who have helped shape Vacation Planner Pro, making it what it is today.
