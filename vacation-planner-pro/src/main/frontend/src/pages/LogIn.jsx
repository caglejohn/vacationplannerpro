import { useState } from 'react';

export default function LogIn() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [companyId, setCompanyId] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!username || !password || companyId) {
      setError('Username, password, and company required');
      return;
    }

    setIsLoading(true);
    // then confirm user
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
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
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
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <div className="form-group">
                <label htmlFor="companyId">Company ID:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  value={companyId}
                  onChange={(e) => setCompanyId(e.target.value)}
                  id="companyId"
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
