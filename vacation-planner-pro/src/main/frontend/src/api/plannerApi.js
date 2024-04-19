import axios from 'axios';

const plannerApi = axios.create({
    // for working on dev
  //baseURL: 'http://localhost:9080',

  // for working on prod
  baseURL: 'http://148.100.108.158:9080',
});

export const postAuth = async (user) => {
  const companyIdAsNumber = parseInt(user.companyId, 10);
  if (isNaN(companyIdAsNumber)) {
    throw new Error('Company Id must be a number');
  }
  const userParsed = {
    username: user.username,
    password: user.password,
    email: 'test@test.com',
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
    email: user.email,
  };
  const resp = await plannerApi.post('/api/employees', userParsed, {
    withCredentials: true,
  });
  return resp;
};

export const getCalendar = async () => {
  const resp = await plannerApi.get('/calendar');
  return resp.data;
};

export default plannerApi;
