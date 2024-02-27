import { useQuery, useMutation, useQueryClient } from 'react-query';
import {
  getVacDays,
  createVacDay,

  // Uncomment the below to use
  //updateVacDay,
  //deleteVacDay,
} from '../api/plannerApi';
import { useState } from 'react';

const Test = () => {
  const [newHours, setNewHours] = useState('');
  const queryClient = useQueryClient();

  const {
    isLoading,
    isError,
    error,
    data: vacDays,
  } = useQuery('vacDays', getVacDays);

  const createDayM = useMutation(createVacDay, {
    onSuccess: () => {
      queryClient.invalidateQueries('vacDays');
    },
  });

  // Functions unused by the test here
  // But try messing around with them if you want :)
  /*
  const updateDayM = useMutation(updateVacDay, {
    onSuccess: () => {
      queryClient.invalidateQueries('vacDays');
    },
  });

  const deleteDayM = useMutation(deleteVacDay, {
    onSuccess: () => {
      queryClient.invalidateQueries('vacDays');
    },
  });
  */

  const handleSubmit = (e) => {
    e.preventDefault();
    createDayM.mutate({ userId: 1, dayId: 2, hours: newHours });
    setNewHours('');
  };

  const testForm = (
    <form onSubmit={handleSubmit}>
      <label htmlFor="new-hours">Enter vacation hours</label>
      <div className="new-hours">
        <input
          type="text"
          id="new-hours"
          value={newHours}
          onChange={(e) => setNewHours(e.target.value)}
          placeholder="Enter number of hours"
        />
      </div>
      <button className="submit">Submit</button>
    </form>
  );

  let body;
  if (isLoading) {
    body = <p>Loading</p>;
  } else if (isError) {
    body = <p>{error.message}</p>;
  } else {
    body = JSON.stringify(vacDays);
  }

  return (
    <main>
      <h1>Fun little api test</h1>
      {testForm}
      {body}
    </main>
  );
};

export default Test;
