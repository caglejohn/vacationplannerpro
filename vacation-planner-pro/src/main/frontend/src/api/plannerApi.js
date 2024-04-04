import axios from 'axios';

const plannerApi = axios.create({
  baseURL: 'http://localhost:9080',
});

export const postAuth = async (user) => {
  const companyIdAsNumber = parseInt(user.companyId, 10);
  if (isNaN(companyIdAsNumber)) {
    throw new Error('Company Id must be a number');
  }
  const userParsed = {
    username: user.username,
    password: user.password,
  };
  const resp = await plannerApi.post('/api/employees/session', userParsed, {
    withCredentials: true,
  });
  return resp;
};

export const postSignup = async (user) => {
  const companyIdAsNumber = parseInt(user.companyId, 10);
  if (isNaN(companyIdAsNumber)) {
    throw new Error('Company Id must be a number');
  }
  const userParsed = {
    username: user.username,
    password: user.password,
  };
  const resp = await plannerApi.post('/api/employees', userParsed, {
    withCredentials: true,
  });
  return resp.status;
};

export const getCalendar = async () => {
  const resp = await plannerApi.get('/calendar');
  return resp.data;
};

export default plannerApi;
