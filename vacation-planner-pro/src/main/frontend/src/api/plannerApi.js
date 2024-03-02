import axios from 'axios';

const plannerApi = axios.create({
  baseURL: 'http://localhost:3000',
});

export const postAuth = async () => {
  //const resp = await plannerApi.post('/users');

  const fakeOk = { status: 201, statusText: 'OK' };
  //const fakeUnauth = { status: 401, statusText: 'Unauthorized' };

  return fakeOk;
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
