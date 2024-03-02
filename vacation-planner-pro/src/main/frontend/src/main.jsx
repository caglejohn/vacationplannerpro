import ReactDOM from 'react-dom/client';
import './index.css';
import { QueryClient, QueryClientProvider } from 'react-query';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Root from './pages/Root';
import Error from './Error';
import LogIn from './pages/LogIn';
//import Index from './pages/Index';
import Calendar from './pages/Calendar';
import SignUp from './pages/SignUp';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <Error />,
    children: [
      { index: true, element: <LogIn /> },
      {
        path: 'login',
        element: <LogIn />,
      },
      {
        path: 'calendar',
        element: <Calendar />,
      },
      {
        path: 'signup',
        element: <SignUp />,
      },
    ],
  },
]);

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')).render(
  <QueryClientProvider client={queryClient}>
    <RouterProvider router={router}></RouterProvider>
  </QueryClientProvider>,
);
