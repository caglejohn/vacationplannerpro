import { useState, useEffect } from 'react';
import '../styles/createVacationRev.css';
import { Link } from 'react-router-dom';
import { postTimeOff, getUserVacations } from '../api/plannerApi';

const CreateVacationRev = () => {
  const [formData, setFormData] = useState({
    reason: '',
    day: '',
    time: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const [status, setStatus] = useState(null);
  const [vacations, setVacations] = useState([]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  useEffect(() => {
    fetchVacations();
  }, []);

  const fetchVacations = async () => {
    try {
      const response = await getUserVacations();
      const userVacations = response.data;
      if (Array.isArray(userVacations)) {
        setVacations(userVacations);
      } else {
        console.error(
          'getUserVacations did not return an array:',
          userVacations,
        );
        setVacations([]);
      }
    } catch (error) {
      console.error('Error fetching vacations: ', error);
      setVacations([]);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const resp = await postTimeOff(formData);
      if (resp.status === 201) {
        setStatus('success');
        fetchVacations();
      }
    } catch (error) {
      if (error.response.status === 401) {
        setStatus(
          'Could not find day or user-- check that you are logged in and submitting for the current calendar year',
        );
      } else {
        setStatus('Server error while creating vacation');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div id="create-page">
      <div id="container">
        <div id="header">
          <div className="nav-container">
            {' '}
            {/*to add logo to top left of the page*/}
            <div className="container"></div>
          </div>
        </div>

        <div className="d-flex">
          <div className="sidebar pt-5">
            <nav className="navbar navbar-expand-lg">
              <div className="container-fluid">
                <button
                  className="navbar-toggler"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#navbarNav"
                  aria-controls="navbarNav"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                >
                  <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                  <ul className="navbar-nav flex-column">
                    <li className="nav-item">
                      <Link className="nav-link" to="/calendar">
                        {' '}
                        {/*back to calendar button*/}
                        Calendar
                      </Link>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>
          </div>

          <div className="update-container">
            <header>
              <h1>Schedule Time Off</h1>
            </header>
            <div className="row">
              <div className="col-md-6" style={{ alignSelf: 'center' }}>
                <form id="timeOffForm" onSubmit={handleSubmit}>
                  <div className="form-row align-items-center">
                    <div className="col-md-6">
                      <div className="form-group">
                        <label htmlFor="reason">Leave Type:</label>
                        <select
                          id="reason"
                          name="reason"
                          className="form-control"
                          value={formData.reason}
                          onChange={handleChange}
                        >
                          <option value="">Select Reason</option>
                          <option value="personal">Personal</option>
                          <option value="vacation">Vacation</option>
                          <option value="sick">Sick</option>
                          <option value="maternity">
                            Maternity/Paternity Leave
                          </option>
                          <option value="holiday">Company Holiday</option>
                          <option value="other">Other</option>
                        </select>
                      </div>
                    </div>
                  </div>

                  <div className="form-row">
                    <div className="col-md-6">
                      <div className="form-group">
                        <label htmlFor="day">Start Date:</label>
                        <input
                          type="date"
                          id="day"
                          name="day"
                          className="form-control"
                          value={formData.day}
                          onChange={handleChange}
                        />
                      </div>
                    </div>
                    <div className="col-md-6">
                      <div className="form-group">
                        <label>Time of Day:</label>
                        <div className="d-flex">
                          <div
                            className="form-check"
                            style={{ marginRight: '1rem' }}
                          >
                            <input
                              className="form-check-input"
                              type="radio"
                              name="time"
                              id="am"
                              value="AM"
                              checked={formData.time === 'AM'}
                              onChange={handleChange}
                            />
                            <label
                              className="form-check-label mr-2"
                              htmlFor="am"
                            >
                              AM
                            </label>
                          </div>
                          <div className="form-check">
                            <input
                              className="form-check-input"
                              type="radio"
                              name="time"
                              id="pm"
                              value="PM"
                              checked={formData.time === 'PM'}
                              onChange={handleChange}
                            />
                            <label className="form-check-label" htmlFor="pm">
                              PM
                            </label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="form-group">
                    <button
                      type="submit"
                      className="btn btn-primary"
                      disabled={isLoading}
                    >
                      {isLoading ? 'Submitting...' : 'Submit'}
                    </button>
                  </div>
                </form>
              </div>

              {/* Status Cards */}
              <div className="col-md-6" style={{ alignSelf: 'center' }}>
                <div className="row" id="cards">
                  <div className="col-md-6 mb-3">
                    <div className="status-card approved">
                      <p className="status-number">3</p>
                      <p className="status-type">Approved</p>
                    </div>
                  </div>
                  <div className="col-md-6 mb-3">
                    <div className="status-card declined">
                      <p className="status-number">0</p>
                      <p className="status-type">Declined</p>
                    </div>
                  </div>
                  <div className="col-md-6 mb-3">
                    <div className="status-card pending">
                      <p className="status-number">1</p>
                      <p className="status-type">Pending</p>
                    </div>
                  </div>
                  <div className="col-md-6 mb-3">
                    <div className="status-card remaining">
                      <p className="status-number">12</p>
                      <p className="status-type">Remaining</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {status === 'success' && (
              <p className="text-success">Vacation created successfully!</p>
            )}
            {status === 'error' && (
              <p className="text-danger">
                Error creating vacation. Please try again later.
              </p>
            )}
            <div className="current-timeoff">
              <h2>My Vacations</h2>
              <ul>
                {vacations.map((vacation, index) => (
                  <li key={index}>{vacation}</li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CreateVacationRev;
