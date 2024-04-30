import axios from 'axios';

const authApi = axios.create({
  // for working on dev
  //baseURL: 'http://localhost:9080',

  // for working on prod
 baseURL: 'http://148.100.108.158:9080',
});

export const postAuth = async (user) => {
  const resp = await authApi.post('/api/employees/session', user, {
    withCredentials: true,
  });
  return resp;
};

export const deleteSession = async () => {
  const resp = await authApi.delete('/api/employees/session', {
    withCredentials: true,
  });
  return resp;
};

export const getAuth = async () => {
  const resp = await authApi.get('/api/employees/session', {
    withCredentials: true,
    timeout: 1000,
  });
  return resp;
};

export const postSignup = async (user) => {
  const resp = await authApi.post('/api/employees', user, {
    withCredentials: true,
  });
  return resp;
};

export default authApi;
