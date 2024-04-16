import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const VacationProfiles = () => {
  const [vacationProfiles, setVacationProfiles] = useState([]);
  const [sortConfig, setSortConfig] = useState({ key: null, direction: '' });

  const mockData = [
    {
      employeeId: 1,
      employeeName: 'Doe, John',
      totalVacationDays: 20,
      sickDays: 5,
      vacationDaysTaken: 8,
      vacationDaysRemaining: 12,
      sickDaysTaken: 2,
      sickDaysRemaining: 3,
    },
    {
      employeeId: 2,
      employeeName: 'Lovelace, Ada',
      totalVacationDays: 18,
      sickDays: 4,
      vacationDaysTaken: 10,
      vacationDaysRemaining: 8,
      sickDaysTaken: 1,
      sickDaysRemaining: 3,
    },
    {
      employeeId: 3,
      employeeName: 'Different, Employee',
      totalVacationDays: 14,
      sickDays: 4,
      vacationDaysTaken: 0,
      vacationDaysRemaining: 14,
      sickDaysTaken: 1,
      sickDaysRemaining: 3,
    },
  ];

  const fetchData = () => {
    setVacationProfiles(mockData);
  };

  React.useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const requestSort = (key) => {
    let direction = 'asc';
    if (
      sortConfig &&
      sortConfig.key === key &&
      sortConfig.direction === 'asc'
    ) {
      direction = 'desc';
    }
    setSortConfig({ key, direction });
  };

  const sortedData = () => {
    const sortableProfiles = [...vacationProfiles];
    if (sortConfig.key !== null) {
      sortableProfiles.sort((a, b) => {
        if (a[sortConfig.key] < b[sortConfig.key]) {
          return sortConfig.direction === 'asc' ? -1 : 1;
        }
        if (a[sortConfig.key] > b[sortConfig.key]) {
          return sortConfig.direction === 'asc' ? 1 : -1;
        }
        return 0;
      });
    }
    return sortableProfiles;
  };

  const sortedProfiles = sortedData();

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
                  <Link className="nav-link" to="/calendar">
                    Calendar
                  </Link>
               </li>
               {/* 
                <li className="nav-item">
                  <Link className="nav-link1" to="/calendar/create">
                    Schedule Vacation
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link1" to="/">
                    Update Vacation
                  </Link>
                </li>
                */}
              </ul>
            </div>
          </div>
        </nav>
      </div>
      <div className="content pt-0">
        <div className="container">
          <h1 className="mt-5 mb-4 text-center">Vacation Reporting</h1>
          <table className="table table-striped">
            <thead>
              <tr>
                <th
                  onClick={() => requestSort('employeeId')}
                  className="text-center"
                >
                  Employee ID
                  {sortConfig.key === 'employeeId' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'employeeId' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('employeeName')}
                  className="text-center"
                >
                  Employee Name
                  {sortConfig.key === 'employeeName' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'employeeName' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('totalVacationDays')}
                  className="text-center"
                >
                  Total Vacation Days
                  {sortConfig.key === 'totalVacationDays' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'totalVacationDays' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('sickDays')}
                  className="text-center"
                >
                  Sick Days
                  {sortConfig.key === 'sickDays' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'sickDays' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('vacationDaysTaken')}
                  className="text-center"
                >
                  Vacation Days Taken
                  {sortConfig.key === 'vacationDaysTaken' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'vacationDaysTaken' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('vacationDaysRemaining')}
                  className="text-center"
                >
                  Vacation Days Remaining
                  {sortConfig.key === 'vacationDaysRemaining' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'vacationDaysRemaining' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('sickDaysTaken')}
                  className="text-center"
                >
                  Sick Days Taken
                  {sortConfig.key === 'sickDaysTaken' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'sickDaysTaken' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
                <th
                  onClick={() => requestSort('sickDaysRemaining')}
                  className="text-center"
                >
                  Sick Days Remaining
                  {sortConfig.key === 'sickDaysRemaining' &&
                    sortConfig.direction === 'asc' && <span>&uarr;</span>}
                  {sortConfig.key === 'sickDaysRemaining' &&
                    sortConfig.direction === 'desc' && <span>&darr;</span>}
                </th>
              </tr>
            </thead>
            <tbody>
              {sortedProfiles.map((profile) => (
                <tr key={profile.employeeId} className="table-row">
                  <td className="text-center">{profile.employeeId}</td>
                  <td className="text-center">{profile.employeeName}</td>
                  <td className="text-center">{profile.totalVacationDays}</td>
                  <td className="text-center">{profile.sickDays}</td>
                  <td className="text-center">{profile.vacationDaysTaken}</td>
                  <td className="text-center">
                    {profile.vacationDaysRemaining}
                  </td>
                  <td className="text-center">{profile.sickDaysTaken}</td>
                  <td className="text-center">{profile.sickDaysRemaining}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
    </div>
    </div>
  );
};

export default VacationProfiles;
