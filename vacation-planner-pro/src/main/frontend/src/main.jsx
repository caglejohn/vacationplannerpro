import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';
import { QueryClient, QueryClientProvider } from 'react-query';
//import { ReactQueryDevTools } from 'react-query/devtools';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')).render(
  <QueryClientProvider client={queryClient}>
    <App />
  </QueryClientProvider>,
);

//Replace the above with below if you want to use react-query dev tools
// Make sure that you uncomment the import statement if you do
/*
ReactDOM.createRoot(document.getElementById('root')).render(
    <QueryClientProvider client={queryClient}>
      <App />
      <ReactQueryDevTools />
    </QueryClientProvider>,
  );
*/
