# Mock API for Frontend

This project launches a mock API server for our frontend. It provides endpoints to access mock data for users and vacation days.

## Getting Started

To install the packages, within this project directory (`src/main/apiMock`), run the command: `npm install`

## Accessing data

To start the server, enter the following command: `npm run mock`

You can access the mock data through the following endpoints:

- `/users`: Retrieve user data.
- `/days`: Retrieve calendar day data.
- `/vacationdays`: Retrieve vacation days

The data is stored in the `db.json` file.

Once the mock API is running, you can open up a second terminal window, navigate to the frontend folder, run `npm run dev` and work with the data from the mock API

## Examples

- To acccess all users, you can use the URL:

  - `http://localhost:3000/users/`

- To access all days, you can use the URL:

  - `http://localhost:3000/days/`

- To access a specific day, you can use the URL:

  - `http://localhost:3000/days/:id`
  - e.g., `http://localhost:3000/days/1`

- To access a specific user, you can use the URL:

  - `http://localhost:3000/users/:id`
  - e.g., `http://localhost:3000/users/1`

- To access the vacation information for a particular user, you can use the URL:

  - `http://localhost:3000/users/:id/vacationdays`
  - e.g., `http://localhost:3000/users/1/vacationdays`

- To access days designated as vacation days for a particular user, you can use the URL:

  - `http://localhost:3000/users/:id/vacationdays_expand=day`
  - e.g., `http://localhost:3000/users/1/vacationdays?_expand=day&_expand=user`

- To access full day and user information for a particular user's vacations, you can use the URL:

  - `http://localhost:3000/users/:id/vacationdays_expand=day&_expand=user`
  - e.g., `http://localhost:3000/users/1/vacationdays?_expand=day&_expand=user`

- To access users who have taken vacation days for a particular day, you can use the URL:

  - `http://localhost:3000/days/:id/vacationdays?_expand=user`
  - e.g., `http://localhost:3000/days/2/vacationdays?_expand=user`

- To access full day and user information for a particular day's vacations, you can use the URL:

  - `http://localhost:3000/days/:id/vacationdays_expand=day&_expand=user`
  - e.g., `http://localhost:3000/days/1/vacationdays?_expand=day&_expand=user`

## Further Documentation

For further documentation about json-server, check out [json-server GitHub repository](https://github.com/typicode/json-server).
