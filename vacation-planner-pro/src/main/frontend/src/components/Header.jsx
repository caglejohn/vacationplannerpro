import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <div
      style={{
        background: '#85DFFF',
        display: 'flex',
        justifyContent: 'center',
        width: '100vw',
      }}
    >
      <div
        style={{
          background: '#FFFFFF',
          borderRadius: '50px',
          padding: '.5rem 3rem',
          minWidth: '95%',
          textAlign: 'left',
          margin: '1rem',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}
      >
        <h1 className="pt-1 mb-0" style={{ color: '#004fff' }}>
          Vacation Planner Pro
        </h1>
        <div>
          <Link to="/about" className="mx-3" style={{ fontSize: '1.3rem' }}>
            About Us
          </Link>
          <Link to="/calendar" className="mx-3" style={{ fontSize: '1.3rem' }}>
            View Calendar
          </Link>
          <Link to="/login" className="mx-3" style={{ fontSize: '1.3rem' }}>
            Log In
          </Link>
        </div>
      </div>
    </div>
  );
}
