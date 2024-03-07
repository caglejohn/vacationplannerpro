import axios from 'axios';

const plannerApi = axios.create({
  baseURL: 'http://localhost:3000',
});

export const postAuth = async (user) => {
  const companyIdAsNumber = parseInt(user.companyId, 10);
  if (isNaN(companyIdAsNumber)) {
    throw new Error('Company Id must be a number');
  }
  const userParsed = {
    ...user,
    companyId: companyIdAsNumber,
  };
  const resp = await plannerApi.post('/login', userParsed, {
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
    ...user,
    companyId: companyIdAsNumber,
  };
  const resp = await plannerApi.post('/signup', userParsed, {
    withCredentials: true,
  });
  return resp.status;
};

export const getCalendar = async () => {
  const resp = await plannerApi.get('/calendar');
  return resp.data;
};

export default plannerApi;
