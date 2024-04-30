import axios from 'axios';

const plannerApi = axios.create({
  // for working on dev
  //baseURL: 'http://localhost:9080',

  // for working on prod
  baseURL: 'http://148.100.108.158:9080',
});

export const getCalendar = async (req) => {
  const resp = await plannerApi.get('/api/calendar', req, {
    withCredentials: true,
  });
  return resp;
};

export const postTimeOff = async (req) => {
  const resp = await plannerApi.post('/api/employee-time-off', req, {
    withCredentials: true,
  });
  return resp;
};

export default plannerApi;
