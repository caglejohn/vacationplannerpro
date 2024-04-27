import { useState } from 'react';
import '../styles/createVacationRev.css';

const CreateVacationRev = () => {
  const [formData, setFormData] = useState({
    leaveType: '',
    startDate: '',
    timeOfDay: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // post data
    } catch (error) {
      console.error('error:', error);
    }
  };

  return (
    <><div className="nav-container">  {/*to add logo to top left of the page*/}
      <div className="container">
      </div>
    </div><div className="update-container">
        <header>
          <h1>Schedule Time Off</h1>
        </header>
        <div className="row">
          <div className="col-md-6" style={{ alignSelf: 'center' }}>
            <form id="timeOffForm" onSubmit={handleSubmit}>
              <div className="form-row align-items-center">
                <div className="col-md-4">
                  <div className="form-group">
                    <label htmlFor="leaveType">Leave Type:</label>
                    <select
                      id="leaveType"
                      name="leaveType"
                      className="form-control"
                      value={formData.leaveType}
                      onChange={handleChange}
                    >
                      <option value="">Select Reason</option>
                      <option value="personal">Personal</option>
                      <option value="vacation">Vacation</option>
                      <option value="sick">Sick</option>
                      <option value="maternity">Maternity/Paternity Leave</option>
                      <option value="holiday">Company Holiday</option>
                      <option value="other">Other</option>
                    </select>
                  </div>
                </div>
              </div>

              <div className="form-row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label htmlFor="startDate">Start Date:</label>
                    <input
                      type="date"
                      id="startDate"
                      name="startDate"
                      className="form-control"
                      value={formData.startDate}
                      onChange={handleChange} />
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Time of Day:</label>
                    <div className="d-flex">
                      <div className="form-check" style={{ marginRight: '1rem' }}>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="timeOfDay"
                          id="am"
                          value="AM"
                          checked={formData.timeOfDay === 'AM'}
                          onChange={handleChange} />
                        <label className="form-check-label mr-2" htmlFor="am">
                          AM
                        </label>
                      </div>
                      <div className="form-check">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="timeOfDay"
                          id="pm"
                          value="PM"
                          checked={formData.timeOfDay === 'PM'}
                          onChange={handleChange} />
                        <label className="form-check-label" htmlFor="pm">
                          PM
                        </label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="form-group">
                <button type="submit" className="btn btn-primary">
                  Submit
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
      </div></>
  );
};

export default CreateVacationRev;
