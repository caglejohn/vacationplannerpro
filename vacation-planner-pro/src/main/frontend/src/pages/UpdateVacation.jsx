import { useState, useEffect } from 'react';
import '../styles/updateVacation.css';
import { Link } from 'react-router-dom';

function UpdateVacation() {
  const [vacations, setVacations] = useState([]);
  const [editData, setEditData] = useState(null);

  useEffect(() => {
    fetchVacations();
  }, []);

  function fetchVacations() {
    const simulatedResponse = [
      {
        id: '1',
        startDate: '2024-04-10',
        endDate: '2024-04-20',
        description: 'Sick Leave',
      },
      {
        id: '2',
        startDate: '2024-06-15',
        endDate: '2024-06-25',
        description: 'Vacation',
      },
      {
        id: '3',
        startDate: '2024-12-20',
        endDate: '2025-01-05',
        description: 'Maternity Leave',
      },
    ];
    setVacations(simulatedResponse);
  }

  function editVacation(vacationId) {
    const vacation = vacations.find((v) => v.id === vacationId);
    setEditData(vacation);
  }

  function saveEdit(vacation) {
    const updatedVacations = vacations.map((v) =>
      v.id === vacation.id ? vacation : v,
    );
    setVacations(updatedVacations);
    setEditData(null); // Close modal after saving
  }

  function deleteVacation(vacationId) {
    const updatedVacations = vacations.filter(
      (vacation) => vacation.id !== vacationId,
    );
    setVacations(updatedVacations);
    displayPopup('Vacation deleted successfully!');
  }

  function displayPopup(message) {
    //setPopupMessage(message);
    //setShowPopup(true);
    setTimeout(() => {
      //setShowPopup(false);
      //setPopupMessage('');
    }, 3000); // Hide the popup after 3 seconds
  }

  return (
    <div id="create-page">
    <div id="container">
      <div id="header">
        <div className="nav-container">  {/*to add logo to top left of the page*/}
          <div className="container">
            </div>
          </div>
        </div>

    <div className="d-flex">
      <div className="sidebar pt-5">
        <nav className="navbar navbar-expand-lg">
          <div className="container-fluid">
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav flex-column">
                <li className="nav-item">
                  <Link className="nav-link" to="/calendar"> {/*back to calendar button*/}
                    Calendar
                  </Link>
               </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    <div className="update">
      <div>
        <h1 className="update">Your Scheduled Time Off</h1>
        <div>
          {vacations.map((vacation) => (
            <div className="vacation-entry" key={vacation.id}>
              <p>
                <strong>{vacation.description}</strong> from{' '}
                {vacation.startDate} to {vacation.endDate}
              </p>
              <button
                onClick={() => editVacation(vacation.id)}
                className="update-button"
              >
                Edit
              </button>
              <button
                onClick={() => deleteVacation(vacation.id)}
                className="delete-button"
              >
                Delete
              </button>
            </div>
          ))}
        </div>
        {editData && (
          <div className="modal">
            <input
              type="text"
              value={editData.description}
              onChange={(e) =>
                setEditData({ ...editData, description: e.target.value })
              }
            />
            <button onClick={() => saveEdit(editData)}>Save Changes</button>
            <button onClick={() => setEditData(null)}>Cancel</button>
          </div>
        )}
      </div>
    </div>
    </div>
    </div>
    </div>
        
  );
}

export default UpdateVacation;
