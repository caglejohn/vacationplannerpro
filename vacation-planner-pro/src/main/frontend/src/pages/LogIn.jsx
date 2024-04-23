import { useState, useRef } from 'react';
import { postAuth } from '../api/plannerApi';
import { useNavigate, Link } from 'react-router-dom';
import Input from '../components/form/Input';

export default function LogIn() {
  const loginRef = useRef();

  const [formError, setFormError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const user = {
        username: loginRef.current.username.value,
        password: loginRef.current.password.value,
        companyId: loginRef.current.companyId.value,
      };
      const response = await postAuth(user);
      if (response.status === 201) {
        navigate('/calendar');
      }
    } catch (error) {
      if (error.response.status === 401) {
        setFormError('Invalid login credentials');
      } else {
        setFormError('Server error while logging in');
      }
    } finally {
      setIsLoading(false);
    }
  };

  // 5-15 alpha-numeric characters and underscores
  const usernameRegex = /^[a-zA-Z0-9_]{5,15}$/;
  // 8-24 characters
  const passwordRegex = /^.{8,24}$/;
  // 1-10 numbers
  const companyRegex = /^[0-9]+$/;

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-6 box pt-5">
          <div className="login-box">
            <form onSubmit={handleSubmit} ref={loginRef}>
              <h1 className="text-center">Log In</h1>
              {formError && (
                <p className="text-danger font-weight-bold mb-2" role="alert">
                  {formError}
                </p>
              )}
              <Input
                label="Username:"
                type="text"
                name="username"
                maxLength={20}
                validationRegex={usernameRegex}
                validationError="5-15 characters and/or underscores"
              />
              <Input
                label="Password:"
                type="password"
                name="password"
                maxLength={30}
                validationRegex={passwordRegex}
                validationError="8-24 characters long"
              />
              <Input
                label="Company Id:"
                type="number"
                name="companyId"
                maxLength={10}
                validationRegex={companyRegex}
                validationError="1-10 numbers"
              />
              <div>
                <button
                  type="submit"
                  className="btn btn-primary btn-lg mt-3"
                  disabled={isLoading}
                >
                  {isLoading ? 'Loading ...' : 'Log In'}
                </button>
                <hr></hr>

                <Link
                  to="/signup"
                  className="btn btn-outline-success w-100"
                  disabled={isLoading}
                >
                  Sign Up
                </Link>
              </div>
            </form>
          </div>
        </div>
        <div className="col-md-6 image-container"></div>
      </div>
    </div>
  );
}
