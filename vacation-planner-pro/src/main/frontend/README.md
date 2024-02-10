# React Project with Vite

## Available Scripts

In the project directory, you can run:

### `npm run dev`

Runs the app in the development mode.

### `npm run build`

Builds the app for production to the `build` folder.

## Working with the Frontend

### First time set up:

1. Within the directory `src/main/frontend`, run `npm install`

### Updating the Frontend

To make desired changes to the frontend:

1. Make the necessary changes to the files.
2. Save the files.
3. While in the directory `src/main/frontend`, enter `npm run dev`.
4. Open the localhost link to view changes. Vite does hot reloads on changes, so you only need to enter `npm run dev` once, and Vite will automatically refresh the view upon saves.

### Building Changes to the Frontend

To build changes to the frontend:

1. Save the files.
2. While in the directory `src/main/frontend`, enter `npm run build`.
3. The build will be copied into the folder `src/main/frontend/dist`
4. Delete the old build-- remove contents of `src/main/webApp`
5. While in the directory `vacationplannerpro/vacation-planner-pro/`, enter `mvn process-resources` to get build from dist -> webApp
6. In the same directory, enter `mvn liberty:dev` to view new build
