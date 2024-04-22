import './index.css';
import {
  createBrowserRouter,
  RouterProvider,
  Navigate,
} from 'react-router-dom';
import { useState, useEffect } from 'react';
import { getAuth } from './api/plannerApi';
import Root from './pages/Root';
import Error from './Error';
import LogIn from './pages/LogIn';
import Calendar from './pages/Calendar';
import SignUp from './pages/SignUp';
import CreateVacation from './pages/CreateVacation';
import VacationProfiles from './pages/Reports';

const isAuthenticated = async () => {
  try {
    const response = await getAuth();
    return response.status === 200;
  } catch (error) {
    return false;
  }
};

// eslint-disable-next-line react/prop-types
const ProtectedRoute = ({ element }) => {
  const [isLoading, setIsLoading] = useState(true);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const checkAuthentication = async () => {
      const authenticated = await isAuthenticated();
      setIsLoggedIn(authenticated);
      setIsLoading(false);
    };

    checkAuthentication();
  }, []);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return isLoggedIn ? element : <Navigate to="/login" />;
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
