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

export const getVacDays = async () => {
  const resp = await plannerApi.get('/vacationdays');
  return resp.data;
};

export const getVacDay = async ({ id }) => {
  const resp = await plannerApi.get(`/vacationdays/${id}`, id);
  return resp.data;
};

export const createVacDay = async (vacDay) => {
  return await plannerApi.post(`/vacationdays/`, vacDay);
};

export const updateVacDay = async (vacDay) => {
  return await plannerApi.patch(`/vacationdays/${vacDay.id}`, vacDay);
};

export const deleteVacDay = async ({ id }) => {
  return await plannerApi.delete(`/vacationdays/${id}`, id);
};

export default plannerApi;
