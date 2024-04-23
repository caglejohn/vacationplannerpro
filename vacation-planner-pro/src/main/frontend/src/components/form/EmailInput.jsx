import React from 'react';

// eslint-disable-next-line react/prop-types
const EmailInput = ({ value, onChange, valid, onFocus, onBlur }) => (
  <div className="form-group">
    <label htmlFor="email">
      Email:
      {valid && value && (
        <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
          <i className="fa fa-check text-success ml-6" aria-hidden="true"></i>
        </span>
      )}
      {!valid && value && (
        <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
          <i className="fa fa-times text-danger ml-6" aria-hidden="true"></i>
        </span>
      )}
    </label>
    <input
      type="email"
      name="email"
      maxLength={50}
      className="form-control mt-1"
      value={value}
      onChange={onChange}
      onFocus={onFocus}
      onBlur={onBlur}
      autoComplete="off"
    />
    {onBlur && !valid && (
      <p className="form-text text-white bg-dark p-2 rounded" id="emailnote">
        Please enter a valid email address
      </p>
    )}
  </div>
);

export default EmailInput;
