import { useState } from 'react';
import { postAuth } from '../api/plannerApi';
import { useNavigate } from 'react-router-dom';

export default function LogIn() {
  const [login, setLogin] = useState({
    username: '',
    password: '',
    companyId: '',
  });
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!login.username || !login.password || !login.companyId) {
      setError('Username, password, and company required');
      return;
    }

    setIsLoading(true);
    try {
      const response = await postAuth(login);
      if (response == 200) {
        navigate('/calendar');
      } else {
        setError('Invalid credentials');
      }
    } catch (error) {
      console.error('Error: ', error);
      setError('Error logging in. Please try again later.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleInput = (e) => {
    setLogin((o) => ({ ...o, [e.target.name]: e.target.value }));
  };

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-5 box">
          <form onSubmit={handleSubmit}>
            <div className="info mt-5 ml-4">
              <h1>Log In</h1>
              {error && (
                <div style={{ color: 'red' }} role="alert">
                  {error}
                </div>
              )}
              <div className="form-group">
                <label htmlFor="username">User Name:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  id="username"
                  name="username"
                  onChange={handleInput}
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <div className="form-group">
                <label htmlFor="password">Password:</label>
                <input
                  className="form-control mt-1"
                  id="password"
                  type="password"
                  name="password"
                  onChange={handleInput}
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <div className="form-group">
                <label htmlFor="companyId">Company Id:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  onChange={handleInput}
                  id="companyId"
                  name="companyId"
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <button
                type="submit"
                className="btn btn-primary btn-lg mt-3"
                disabled={isLoading}
              >
                {isLoading ? 'Loading ...' : 'Login'}
              </button>
              <a
                className="btn btn-success mt-3 btn-lg"
                style={{ marginLeft: '1rem' }}
                href={`/signup`}
              >
                Sign Up
              </a>
            </div>
          </form>
        </div>
        <div
          className="col-md-7 box"
          style={{ paddingLeft: '0px', paddingRight: '5rem' }}
        >
          <img src="/logo.jpeg" alt="Company Logo" id="company-logo" />
        </div>
      </div>
    </div>
  );
}
