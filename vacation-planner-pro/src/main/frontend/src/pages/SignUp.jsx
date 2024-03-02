import { useState } from 'react';

export default function SignUp() {
  const [signup, setSignup] = useState({
    username: '',
    password: '',
    companyId: '',
  });

  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!signup.username || !signup.password || signup.companyId) {
      setError('Username, password, and company required');
      return;
    }
    setIsLoading(true);
    // then confirm user
  };

  // On update of input value, set new value
  const handleInput = (e) => {
    // Update the login values according to targeted name and value of event
    setSignup((o) => ({ ...o, [e.target.name]: [e.target.value] }));
  };

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-5 box">
          <form onSubmit={handleSubmit}>
            <div className="info mt-5 ml-4">
              <h1>Sign Up</h1>
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
                  onChange={handleInput}
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <div className="form-group">
                <label htmlFor="companyId">Company ID:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  onChange={handleInput}
                  id="companyId"
                  aria-required="true"
                  aria-invalid={!!error}
                />
              </div>
              <button
                type="submit"
                className="btn btn-success btn-lg mt-3"
                disabled={isLoading}
              >
                {isLoading ? 'Loading ...' : 'Sign Up'}
              </button>
              <a
                className="btn btn-primary mt-3 btn-lg"
                style={{ marginLeft: '1rem' }}
                href={`/login`}
              >
                Log In
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
