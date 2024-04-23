import React from 'react';

// eslint-disable-next-line react/prop-types
const ConfirmPasswordInput = ({ value, onChange, valid, onFocus, onBlur }) => (
  <div className="form-group">
    <label htmlFor="match">
      Confirm password:
      {valid && (
        <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
          <i className="fa fa-check text-success ml-1" aria-hidden="true"></i>
        </span>
      )}
      {!valid && value && (
        <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
          <i className="fa fa-times text-danger ml-1" aria-hidden="true"></i>
        </span>
      )}
    </label>
    <input
      className="form-control mt-1"
      id="match"
      type="password"
      name="match"
      value={value}
      onChange={onChange}
      onFocus={onFocus}
      onBlur={onBlur}
      maxLength={30}
    />
    {onBlur && !valid && (
      <p className="form-text text-white bg-dark p-2 rounded" id="matchnote">
        Must match previously entered password
      </p>
    )}
  </div>
);

export default ConfirmPasswordInput;
