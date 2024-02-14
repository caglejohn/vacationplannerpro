import Logo from '../assets/images/logo.jpeg';

function LogIn() {
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
              <button type="submit" className="btn btn-primary btn-lg mt-3">
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

export default LogIn;
