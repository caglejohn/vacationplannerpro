import React from 'react';
import '../styles/createVacation.css';
import { Link } from 'react-router-dom';


function CreateVacation() {
  return (
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
                  Calendar
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
    <div className="create-vac container-fluid col-md-6 mt-4">
      <h1>Schedule Time Off</h1>
      <form className="col-md-8" id="timeOffForm">
        <div className="form-group">
          <label htmlFor="leaveType">Leave Type:</label>
          <select id="leaveType" name="leaveType" required>
            <option value="annualLeave">Paid Leave</option>
            <option value="sickLeave">Sick Leave</option>
            <option value="unpaidLeave">Unpaid Leave</option>
            <option value="maternityLeave">Maternity/Paternity Leave</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="startDate">Start Date:</label>
          <input type="date" id="startDate" name="startDate" required />
        </div>
        <div className="form-group">
          <label htmlFor="endDate">End Date:</label>
          <input type="date" id="endDate" name="endDate" required />
        </div>
        <div className="form-group">
          <label htmlFor="reason">Reason for Leave:</label>
          <textarea id="reason" name="reason" rows="4" required></textarea>
        </div>
        <button type="submit">Submit Time Off Request</button>
      </form>
    </div>
    </div>
  );
}

export default CreateVacation;
