import Logo from '/logo.jpeg';
import { useEffect, useState } from 'react';

export default function LogIn() {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState();

  // Get users

  useEffect(() => {
    const fetchUsers = async () => {
      setIsLoading(true);
      try {
        // get user data from mock api
        fetch('http://localhost:3000/users')
          .then((res) => {
            return res.json();
          })
          // log user data in the browser, in the inspect tool, on the console
          .then((data) => {
            console.log(data);
          });
      } catch (e) {
        setError(e);
      } finally {
        setIsLoading(false);
      }
    };
    fetchUsers();
  }, []);

  /*
  // Post new user
  useEffect(() => {
    const postUser = async () => {
      setIsLoading(true);
      try {
        const request = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            id: 3,
            username: 'username',
            password: 'password',
            companyid: 1,
            accruals: 0,
          }),
        };
        // post user data to api
        fetch('http://localhost:3000/users', request).then((res) => {
          return res.json();
        });
      } catch (e) {
        setError(e);
      } finally {
        setIsLoading(false);
      }
    };
    postUser();
  }, []);
*/
  if (error) {
    return <div>Error</div>;
  }
  if (isLoading) {
    return <div>Loading</div>;
  }
  return (
    <>
      <div className="container-fluid">
        <div className="row">
          <div className="col-md-5 box">
            <div className="info mt-5 ml-4">
              <h1>Log In</h1>
              <div className="form-group">
                <label htmlFor="userName">User Name:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  id="userName"
                ></input>
              </div>
              <div className="form-group">
                <label htmlFor="password">Password:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  id="password"
                ></input>
              </div>
              <div className="form-group">
                <label htmlFor="token">Company ID:</label>
                <input
                  type="text"
                  className="form-control mt-1"
                  id="token"
                ></input>
              </div>
              <button type="button" className="btn btn-primary btn-lg mt-3">
                Submit
              </button>
            </div>
          </div>
          <div
            className="col-md-7 box"
            style={{ paddingLeft: '0px', paddingRight: '5rem' }}
          >
            <img src={Logo} alt="Company Logo"></img>
          </div>
        </div>
      </div>
    </>
  );
}
