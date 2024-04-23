/* eslint-disable react/prop-types */
import React, { useState } from 'react';

const Input = ({
  label,
  type,
  name,
  maxLength,
  validationRegex,
  validationError,
  defaultValue = '',
  ...rest
}) => {
  const [value, setValue] = useState(defaultValue);
  const [isValid, setIsValid] = useState(true);

  const handleChange = (e) => {
    const newValue = e.target.value;
    setValue(newValue);
    const isValid = validationRegex.test(newValue);
    setIsValid(isValid);
  };

  return (
    <div className="form-group mb-2">
      <label htmlFor={name}>
        {label}
        {!isValid && (
          <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
            <i className="fa fa-times text-danger ml-6" aria-hidden="true"></i>
          </span>
        )}
      </label>
      <input
        type={type}
        className="form-control mt-1 mb-0"
        id={name}
        name={name}
        maxLength={maxLength}
        value={value}
        aria-required="true"
        aria-invalid={!isValid}
        aria-describedby={`${name}note`}
        onChange={handleChange}
        {...rest}
      />
      {!isValid && (
        <small className="text-danger" role="alert" id={`${name}note`}>
          {validationError}
        </small>
      )}
    </div>
  );
};

export default Input;
