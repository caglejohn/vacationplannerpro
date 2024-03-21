import React from 'react';
import '../styles/createVacation.css';

function CreateVacation() {
  return (
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
  );
}

export default CreateVacation;
