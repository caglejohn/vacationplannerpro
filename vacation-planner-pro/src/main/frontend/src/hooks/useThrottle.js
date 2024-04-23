import { useState, useCallback } from 'react';

const useThrottle = (apiCall, delay = 500) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const throttledApiCall = useCallback(
    (...args) => {
      setIsLoading(true);
      setError(null);
      let timeoutId;

      const apiCallWithTimeout = async () => {
        try {
          const response = await apiCall(...args);
          setData(response);
        } catch (err) {
          setError(err);
        } finally {
          setIsLoading(false);
          timeoutId = undefined;
        }
      };

      if (timeoutId) {
        clearTimeout(timeoutId);
      }

      timeoutId = setTimeout(apiCallWithTimeout, delay);
    },
    [apiCall, delay],
  );

  return { data, error, isLoading, throttledApiCall };
};

export default useThrottle;
