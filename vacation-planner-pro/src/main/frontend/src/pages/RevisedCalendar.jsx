import React, { useState, useEffect } from 'react';
import { getCalendar } from '../api/plannerApi';
// import CalendarMonth from '../components/CalendarMonth';

const RevisedCalendarPage = () => {
  const [calMonth, setCalMonth] = useState([]);

  useEffect(() => {
    const fetch = async () => {
      try {
        // just for test purposes since we only have half days for month of jan 2024
        const testMonth = {
          month: 1,
          year: 2024,
        };
        // get days and vacations for given month and year
        const response = await getCalendar(testMonth);
        setCalMonth(response.data);
      } catch (error) {
        console.error('Error fetching calendar data:', error);
      }
    };

    fetch();
  }, []);

  // This is where we'll display the data we get from the api call
  return <div>{/* <CalendarMonth month={calMonth} /> */}</div>;
};

export default RevisedCalendarPage;
