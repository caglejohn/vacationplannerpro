import React from 'react';

// eslint-disable-next-line react/prop-types
const PasswordInput = ({ value, onChange, valid, onFocus, onBlur }) => (
  <div className="form-group">
    <label htmlFor="password">
      Password:
      {valid && (
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
      className="form-control mt-1"
      id="password"
      type="password"
      name="password"
      value={value}
      onChange={onChange}
      onFocus={onFocus}
      onBlur={onBlur}
      maxLength={30}
    />
    {onBlur && !valid && (
      <p className="form-text text-white bg-dark p-2 rounded" id="passwordnote">
        8 to 24 characters long
      </p>
    )}
  </div>
);

export default PasswordInput;
