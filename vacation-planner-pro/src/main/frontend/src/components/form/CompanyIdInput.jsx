import { useState, useEffect } from 'react';
import { useDebounce } from '../../hooks/UseDebounce';

// eslint-disable-next-line react/prop-types
const CompanyIdInput = ({ value, onChange, valid, onFocus, onBlur }) => {
  const debouncedValue = useDebounce(value, 300); // Debounce for 300ms

  return (
    <div className="form-group">
      <label htmlFor="companyId">
        Company Id:
        {valid && (
          <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
            <i className="fa fa-check text-success ml-6" aria-hidden="true"></i>
          </span>
        )}
        {!valid && debouncedValue && (
          <span style={{ marginLeft: '5px', verticalAlign: 'middle' }}>
            <i className="fa fa-times text-danger ml-6" aria-hidden="true"></i>
          </span>
        )}
      </label>
      <input
        type="number"
        name="company"
        maxLength={10}
        className="form-control mt-1"
        value={debouncedValue}
        onChange={onChange}
        onFocus={onFocus}
        onBlur={onBlur}
        autoComplete="off"
      />
      {onBlur && !valid && (
        <p
          className="form-text text-white bg-dark p-2 rounded"
          id="companynote"
        >
          At least 1 or more numbers
        </p>
      )}
    </div>
  );
};

export default CompanyIdInput;
