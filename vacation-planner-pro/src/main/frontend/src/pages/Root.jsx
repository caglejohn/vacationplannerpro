import { Outlet, useLocation } from 'react-router-dom';
import Header from '../components/Header';

export default function Root() {
  const location = useLocation();
  const pathname = location.pathname;

  const headerPaths = ['/login', '/signup', '/about'];

  const showHeader = headerPaths.includes(pathname);

  return (
    <main>
      {showHeader && <Header />}
      <Outlet />
    </main>
  );
}
