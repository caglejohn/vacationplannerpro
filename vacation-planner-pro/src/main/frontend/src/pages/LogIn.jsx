import { useState, useRef } from 'react';
import { postAuth } from '../api/plannerApi';
import { useNavigate, Link } from 'react-router-dom';

export default function LogIn() {
  const loginRef = useRef();

  const [validUsername, setValidUsername] = useState(true);
  const [validPassword, setValidPassword] = useState(true);
  const [validCompany, setValidCompany] = useState(true);

  const usernameRegex = /^[a-zA-Z0-9_]{5,15}$/;
  const passwordRegex = /.{8,24}/;
  const companyRegex = /^[0-9]+$/;

  const [formError, setFormError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    let valC = validateCompany();
    let valU = validateUsername();
    let valP = validatePassword();
    if (!valU || !valC || !valP) {
      setIsLoading(false);
      return;
    }
    try {
      const user = {
        username: loginRef.current.username.value,
        password: loginRef.current.password.value,
        companyId: loginRef.current.companyId.value,
      };
      const response = await postAuth(user);
      console.log(response);
      if (response.status == 201) {
        navigate('/calendar');
      }
    } catch (error) {
      setFormError('Invalid login credentials');
    } finally {
      setIsLoading(false);
    }
  };

  function validateUsername() {
    const result = usernameRegex.test(loginRef.current.username.value);
    setValidUsername(result);
    return result;
  }

  function validatePassword() {
    const result = passwordRegex.test(loginRef.current.password.value);
    setValidPassword(result);
    return result;
  }

  function validateCompany() {
    const result = companyRegex.test(loginRef.current.companyId.value);
    setValidCompany(result);
    return result;
  }

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
              <div className="form-group mb-2">
                <label htmlFor="username">
                  Username:
                  {!validUsername && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-times text-danger ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                </label>
                <input
                  type="text"
                  className="form-control mt-1 mb-0"
                  id="username"
                  name="username"
                  maxLength={20}
                  aria-required="true"
                  aria-invalid={!!validUsername}
                  aria-describedby="usernote"
                />
                {!validUsername && (
                  <small
                    className="text-danger"
                    style={{ fontWeight: 400 }}
                    role="alert"
                    id="usernote"
                  >
                    5-15 characters and/or underscores
                  </small>
                )}
              </div>
              <div className="form-group mb-2">
                <label htmlFor="password">
                  Password:
                  {!validPassword && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-times text-danger ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                </label>
                <input
                  className="form-control mt-1 mb-0"
                  id="password"
                  type="password"
                  name="password"
                  maxLength={30}
                  aria-required="true"
                  aria-invalid={!!validPassword}
                  aria-describedby="passwordnote"
                />
                {!validPassword && (
                  <small className="text-danger" role="alert" id="passwordnote">
                    8-24 characters long
                  </small>
                )}
              </div>
              <div className="form-group mb-1">
                <label htmlFor="companyId">
                  Company Id:
                  {!validCompany && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-times text-danger ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                </label>
                <input
                  type="number"
                  className="form-control mt-1 mb-0"
                  id="companyId"
                  name="companyId"
                  maxLength={10}
                  aria-required="true"
                  aria-invalid={!!validCompany}
                  aria-describedby="companynote"
                />
                {!validCompany && (
                  <small className="text-danger" role="alert" id="companynote">
                    1 or more numbers
                  </small>
                )}
              </div>
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
