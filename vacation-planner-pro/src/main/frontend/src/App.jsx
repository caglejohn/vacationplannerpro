import './index.css';
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
} from 'react-router-dom';
import Root from './pages/Root';
import Error from './Error';
import LogIn from './pages/LogIn';
import Calendar from './pages/Calendar';
import SignUp from './pages/SignUp';
import CreateVacation from './pages/CreateVacation';
import VacationProfiles from './pages/Reports';

const isAuthenticated = () => {
  const cookie = document.cookie
    .split(';')
    .find((cookie) => cookie.trim().startsWith('authToken'));
  return cookie !== undefined;
};

// eslint-disable-next-line react/prop-types
const ProtectedRoute = ({ element }) => {
  return isAuthenticated() ? element : <Navigate to="/login" />;
};

const routes = [
  {
    path: '/',
    element: <Root />,
    errorElement: <Error />,
    children: [
      {
        index: true,
        element: isAuthenticated() ? (
          <Navigate to="/calendar" />
        ) : (
          <Navigate to="/login" />
        ),
      },
      {
        path: 'login',
        element: <LogIn />,
      },
      {
        path: 'reports',
        element: <ProtectedRoute element={<VacationProfiles />} />,
      },
      {
        path: 'calendar',
        element: <ProtectedRoute element={<Calendar />} />,
      },
      {
        path: 'calendar/create',
        element: <ProtectedRoute element={<CreateVacation />} />,
      },
      {
        path: 'signup',
        element: <SignUp />,
      },
    ],
  },
];
const router = createBrowserRouter(routes);

export default function App() {
  return (
    <RouterProvider router={router}>{router.startupElement}</RouterProvider>
  );
}
