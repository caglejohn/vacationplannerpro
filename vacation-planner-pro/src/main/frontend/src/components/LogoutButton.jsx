import React from 'react';
import { deleteSession } from '../api/plannerApi';
import { useNavigate } from 'react-router-dom';
import '../styles/styleCal.css';

const LogoutButton = () => {
  const navigate = useNavigate();
  const handleLogout = async () => {
    try {
      await deleteSession();
      navigate('/login');
    } catch (error) {
      console.error('Error logging out:', error);
    }
  };

  return (
    <button className="logout-button" id="logoutButton" onClick={handleLogout}>
      Log Out
    </button>
  );
};

export default LogoutButton;
