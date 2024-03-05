import { useState, useRef, useEffect } from 'react';
import { postSignup } from '../api/plannerApi';
import { useNavigate } from 'react-router-dom';

export default function SignUp() {
  const usernameRegex = /^[a-zA-Z0-9_]{5,15}$/;
  const passwordRegex = /.{8,24}/;
  const companyRegex = /^[0-9]+$/;

  const signupRef = useRef();
  const errorRef = useRef();

  const [username, setUsername] = useState('');
  const [validUsername, setValidUsername] = useState(false);
  const [usernameFocus, setUsernameFocus] = useState(false);

  const [password, setPassword] = useState('');
  const [validPassword, setValidPassword] = useState(false);
  const [passwordFocus, setPasswordFocus] = useState(false);

  const [match, setMatch] = useState('');
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [company, setCompany] = useState('');
  const [validCompany, setValidCompany] = useState(false);
  const [companyFocus, setCompanyFocus] = useState(false);

  const [error, setError] = useState('');
  const [isValid, setIsValid] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    const valUser = usernameRegex.test(username);
    const valPassword = passwordRegex.test(password);
    const valCompany = companyRegex.test(company);
    if (!valUser || !valPassword || !valCompany) {
      setError('Invalid submission');
      return;
    }
    const newUser = {
      username: username.trim(),
      password: password,
      companyId: company,
      accruals: 8,
    };
    try {
      const response = await postSignup(newUser);
      if (response == 201) {
        navigate('/login');
      } else {
        setError('Invalid credentials');
      }
    } catch (error) {
      console.error('Error: ', error);
      setError('Error signing up. Please try again later.');
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    signupRef.current.focus();
  }, []);

  useEffect(() => {
    const result = usernameRegex.test(username);
    setValidUsername(result);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [username]);

  useEffect(() => {
    const result = passwordRegex.test(password);
    setValidPassword(result);
    const isMatch = password === match ? true : false;
    setValidMatch(isMatch);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [password, match]);

  useEffect(() => {
    const result = companyRegex.test(company);
    setValidCompany(result);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [company]);

  useEffect(() => {
    setError('');
  }, [username, password, match, company]);

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-5 box">
          <form onSubmit={handleSubmit}>
            <div className="info ml-4">
              <h1>Sign Up</h1>
              {error && (
                <p style={{ color: 'red' }} role="alert" aria-live="assertive">
                  {error}
                </p>
              )}
              <div className="form-group">
                <label htmlFor="username">
                  Username:
                  {validUsername && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!validUsername && username && (
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
                  className="form-control mt-1"
                  autoComplete="off"
                  id="username"
                  ref={signupRef}
                  onChange={(e) => setUsername(e.target.value)}
                  aria-required="true"
                  aria-invalid={validUsername ? 'false' : 'true'}
                  aria-describedby="usernote"
                  onFocus={() => setUsernameFocus(true)}
                  onBlur={() => setUsernameFocus(false)}
                  maxLength={20}
                />
              </div>
              {usernameFocus && !validUsername && username && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="usernote"
                >
                  5 to 15 characters long
                  <br />
                  Only alphanumeric characters & underscores
                </p>
              )}
              <div className="form-group">
                <label htmlFor="password">
                  Password:
                  {validPassword && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!validPassword && password && (
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
                  className="form-control mt-1"
                  id="password"
                  type="password"
                  onChange={(e) => setPassword(e.target.value)}
                  maxLength={30}
                  aria-required="true"
                  aria-invalid={validPassword ? 'false' : 'true'}
                  aria-describedby="passwordnote"
                  onFocus={() => setPasswordFocus(true)}
                  onBlur={() => setPasswordFocus(false)}
                />
              </div>
              {passwordFocus && !validPassword && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="passwordnote"
                >
                  8 to 24 characters long
                </p>
              )}
              <div className="form-group">
                <label htmlFor="match">
                  Confirm password:
                  {validMatch && match && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!validMatch && match && (
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
                  className="form-control mt-1"
                  id="match"
                  type="password"
                  maxLength={30}
                  onChange={(e) => setMatch(e.target.value)}
                  aria-required="true"
                  aria-invalid={validMatch ? 'false' : 'true'}
                  onFocus={() => setMatchFocus(true)}
                  onBlur={() => setMatchFocus(false)}
                  aria-describedby="matchnote"
                />
              </div>
              {matchFocus && !validMatch && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="matchnote"
                >
                  Must match previously entered password
                </p>
              )}
              <div className="form-group">
                <label htmlFor="companyId">
                  Company Id:
                  {validCompany && company && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!validCompany && company && (
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
                  maxLength={10}
                  className="form-control mt-1"
                  onChange={(e) => setCompany(e.target.value)}
                  id="companyId"
                  aria-required="true"
                  aria-invalid={validCompany ? 'false' : 'true'}
                  aria-describedby="companynote"
                  onFocus={() => setCompanyFocus(true)}
                  onBlur={() => setCompanyFocus(false)}
                />
              </div>
              {companyFocus && !validCompany && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="companynote"
                >
                  At least 1 or more numbers
                </p>
              )}
              <button
                type="submit"
                className="btn btn-success btn-lg mt-1"
                disabled={
                  isLoading ||
                  !validCompany ||
                  !validMatch ||
                  !validPassword ||
                  !validUsername
                }
              >
                {isLoading ? 'Loading ...' : 'Sign Up'}
              </button>
              <p className="mt-3">
                Already registered? <a href={`/login`}>Log In</a>
              </p>
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
