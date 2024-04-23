import { useState, useEffect, useRef } from 'react';

export const useDebounce = (callback, delay) => {
  const [debouncedValue, setDebouncedValue] = useState(null);
  const handler = useRef(null);

  useEffect(() => {
    if (handler.current) {
      clearTimeout(handler.current);
    }
    handler.current = setTimeout(() => {
      setDebouncedValue(callback);
    }, delay);

    return () => {
      clearTimeout(handler.current);
    };
  }, [callback, delay]);

  return debouncedValue;
};
