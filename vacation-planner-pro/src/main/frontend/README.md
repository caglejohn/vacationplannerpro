# React Project with Vite

## Available Scripts

In the project directory, you can run:

### `npm run dev`

Runs the app in the development mode.

### `npm run test`

Runs tests for the app

### `npm run build`

Builds the app for production to the `dist` folder.

### `npm run lint`

Checks files for errors, bugs, and sketchy code (or install the extension ESLint on VSCode/IntelliJ for this to automatically run)

### `npm run format`

Reformats code to our shared code standard (or install the extension Prettier on VSCode/IntelliJ for this to automatically run upon save)

## Working with the Frontend

### First time set up:

1. Enter `node -v` into the terminal. If the response is not `v20.11.0`, then download the latest LTS version of node here: [https://nodejs.org/en/download]
2. If you needed to install the latest Node version, close out your IDE or text-editor, re-open it, and then navigate back to the directory `src/main/frontend`
3. Within the directory `src/main/frontend`, run `npm install` to download all the package dependencies

### Updating the Frontend

To make desired changes to the frontend:

1. Make the necessary changes to the files.
2. Save the files.
3. While in the directory `src/main/frontend`, enter `npm run dev`.
4. Open the localhost link to view changes. Vite does hot reloads on changes, so you only need to enter `npm run dev` once, and Vite will automatically refresh the view upon saves.
5. If committing, in the directory `src/main/frontend`, run `npm run lint` to check for bugs

### Building Changes to the Frontend

To build changes to the frontend:

1. Save the files.
2. In the directory `src/main/frontend`, run `npm run lint` to check for bugs
3. In same directory, enter `npm run build`.
4. The build will be copied into the folder `src/main/frontend/dist`
5. Delete the old build-- remove contents of `src/main/webApp`
6. While in the directory `vacationplannerpro/vacation-planner-pro/`, enter `mvn process-resources` to get build from dist -> webApp
7. In the same directory, enter `mvn liberty:dev` to view new build
