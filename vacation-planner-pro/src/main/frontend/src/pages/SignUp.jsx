import { useState, useRef, useEffect } from 'react';
import { postSignup } from '../api/plannerApi';
import { useNavigate } from 'react-router-dom';

export default function SignUp() {
  const [form, setForm] = useState({
    username: '',
    password: '',
    match: '',
    company: '',
  });
  const [valid, setValid] = useState({
    username: false,
    password: false,
    match: false,
    company: false,
  });
  const [focus, setfocus] = useState({
    username: false,
    password: false,
    match: false,
    company: false,
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  const navigate = useNavigate();
  const signupRef = useRef();

  const usernameRegex = /^[a-zA-Z0-9_]{5,15}$/;
  const passwordRegex = /.{8,24}/;
  const companyRegex = /^[0-9]+$/;

  const handleInput = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  useEffect(() => {
    signupRef.current.focus();
  }, [signupRef]);

  useEffect(() => {
    const { username, password, match, company } = form;
    const userVal = usernameRegex.test(username);
    const passwordVal = passwordRegex.test(password);
    const matchVal = password === match;
    const companyVal = companyRegex.test(company);
    setValid({
      username: userVal,
      password: passwordVal,
      match: matchVal,
      company: companyVal,
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [form]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    const { username, password, company } = form;

    const isValid = Object.values(valid).every((val) => val === true);
    if (!isValid) {
      setError('Invalid submission');
      setIsLoading(false);
      return;
    }

    const newUser = {
      username: username.trim(),
      password: password,
      companyId: company,
      accruals: 8,
    };

    try {
      const resp = await postSignup(newUser);
      if (resp === 201) {
        navigate('/login');
      } else {
        setError('Error signing up, please try later');
      }
    } catch (error) {
      console.error('Error: ', error);
      setError(error.response.data.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleFocus = (field, isFocused) => {
    setfocus((prev) => ({
      ...prev,
      [field]: isFocused,
    }));
  };

  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-6 box pt-4">
          <div className="login-box">
            <form onSubmit={handleSubmit}>
              <h1 className="text-center">Sign Up</h1>
              {error && (
                <p
                  className="text-danger mb-2"
                  role="alert"
                  aria-live="assertive"
                >
                  {error}
                </p>
              )}
              <div className="form-group">
                <label htmlFor="username">
                  Username:
                  {valid.username && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!valid.username && form.username && (
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
                  name="username"
                  autoComplete="off"
                  id="username"
                  ref={signupRef}
                  onChange={handleInput}
                  aria-required="true"
                  aria-invalid={!!valid.username}
                  aria-describedby="usernote"
                  onFocus={() => handleFocus('username', true)}
                  onBlur={() => handleFocus('username', false)}
                  maxLength={20}
                />
              </div>
              {focus.username && !valid.username && form.username && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="usernote"
                >
                  5 to 15 characters long
                </p>
              )}
              <div className="form-group">
                <label htmlFor="password">
                  Password:
                  {valid.password && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!valid.password && form.password && (
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
                  name="password"
                  onChange={handleInput}
                  maxLength={30}
                  aria-required="true"
                  aria-invalid={!!valid.password}
                  aria-describedby="passwordnote"
                  onFocus={() => handleFocus('password', true)}
                  onBlur={() => handleFocus('password', false)}
                />
              </div>
              {focus.password && !valid.password && (
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
                  {valid.match && form.match && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-1"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!valid.match && form.match && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-times text-danger ml-1"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                </label>
                <input
                  className="form-control mt-1"
                  id="match"
                  type="password"
                  name="match"
                  maxLength={30}
                  onChange={handleInput}
                  aria-required="true"
                  aria-invalid={!!valid.match}
                  onFocus={() => handleFocus('match', true)}
                  onBlur={() => handleFocus('match', false)}
                  aria-describedby="matchnote"
                />
              </div>
              {focus.match && !valid.match && (
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
                  {valid.company && form.company && (
                    <span
                      style={{ marginLeft: '5px', verticalAlign: 'middle' }}
                    >
                      <i
                        className="fa fa-check text-success ml-6"
                        aria-hidden="true"
                      ></i>
                    </span>
                  )}
                  {!valid.company && form.company && (
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
                  name="company"
                  maxLength={10}
                  className="form-control mt-1"
                  onChange={handleInput}
                  id="companyId"
                  autoComplete="off"
                  aria-required="true"
                  aria-invalid={!!valid.company}
                  aria-describedby="companynote"
                  onFocus={() => handleFocus('company', true)}
                  onBlur={() => handleFocus('company', false)}
                />
              </div>
              {focus.company && !valid.company && (
                <p
                  className="form-text text-white bg-dark p-2 rounded"
                  id="companynote"
                >
                  At least 1 or more numbers
                </p>
              )}

              <button
                type="submit"
                className="btn btn-success btn-lg"
                disabled={
                  isLoading ||
                  !valid.company ||
                  !valid.match ||
                  !valid.password ||
                  !valid.username
                }
              >
                {isLoading ? 'Loading ...' : 'Sign Up'}
              </button>

              <hr></hr>

              <a
                href={`/login`}
                className="btn btn-outline-primary w-100"
                disabled={isLoading}
              >
                Log In
              </a>
            </form>
          </div>
        </div>
        <div className="col-md-6 image-container2"></div>
      </div>
    </div>
  );
}
