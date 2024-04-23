import React from 'react';

// eslint-disable-next-line react/prop-types
const UsernameInput = ({ value, onChange, valid, onFocus, onBlur }) => (
  <div className="form-group">
    <label htmlFor="username">
      Username:
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
      type="text"
      className="form-control mt-1"
      name="username"
      autoComplete="off"
      id="username"
      value={value}
      onChange={onChange}
      onFocus={onFocus}
      onBlur={onBlur}
      maxLength={20}
    />
    {onBlur && !valid && value && (
      <p className="form-text text-white bg-dark p-2 rounded" id="usernote">
        5-15 alphanumeric chars and/or underscores
      </p>
    )}
  </div>
);

export default UsernameInput;
