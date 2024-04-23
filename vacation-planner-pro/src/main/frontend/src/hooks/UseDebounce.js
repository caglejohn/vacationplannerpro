import { useState, useEffect, useRef } from 'react';

export const useDebounce = (value, delay) => {
  const [debouncedValue, setDebouncedValue] = useState(value);
  const handler = useRef(null);

  useEffect(() => {
    if (handler.current) {
      clearTimeout(handler.current);
    }

    handler.current = setTimeout(() => {
      setDebouncedValue(value); // Use value from closure
    }, delay);

    return () => {
      clearTimeout(handler.current);
    };
  }, [value, delay]);

  return debouncedValue;
};
