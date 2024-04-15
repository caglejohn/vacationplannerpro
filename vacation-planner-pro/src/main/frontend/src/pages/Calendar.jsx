import '../styles/styleCal.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Calendar() {
  const [nav, setNav] = useState(0);

  const navigate = useNavigate();

  const handleCreate = () => {
    navigate('/calendar/create');
  };

  const handleReports = () => {
    navigate('/reports');
  };

  useEffect(() => {
    const calendar = document.getElementById('calendar');
    const weekdays = [
      'Sunday',
      'Monday',
      'Tuesday',
      'Wednesday',
      'Thursday',
      'Friday',
      'Saturday',
    ];

    function load() {
      const date = new Date();

      if (nav !== 0) {
        date.setMonth(new Date().getMonth() + nav);
      }

      const day = date.getDate();
      const month = date.getMonth();
      const year = date.getFullYear();

      const firstDayOfMonth = new Date(year, month, 1);
      const daysInMonth = new Date(year, month + 1, 0).getDate();

      const dateString = firstDayOfMonth.toLocaleDateString('en-us', {
        weekday: 'long',
        year: 'numeric',
        month: 'numeric',
        day: 'numeric',
      });
      const paddingDays = weekdays.indexOf(dateString.split(', ')[0]);

      document.getElementById('monthDisplay').innerText =
        `${date.toLocaleDateString('en-us', { month: 'long' })} ${year}`;

      calendar.innerHTML = '';

      for (let i = 1; i <= paddingDays + daysInMonth; i++) {
        const daySquare = document.createElement('div');
        daySquare.classList.add('day');

        if (i > paddingDays) {
          daySquare.innerText = i - paddingDays;

          if (i - paddingDays === day && nav === 0) {
            daySquare.id = 'currentDay';
          }
        } else {
          daySquare.classList.add('padding');
        }

        calendar.appendChild(daySquare);
      }
    }

    function initButtons() {
      document.getElementById('nextButton').addEventListener('click', () => {
        setNav(nav + 1);
      });

      document.getElementById('backButton').addEventListener('click', () => {
        setNav(nav - 1);
      });
    }

    initButtons();
    load();

    return () => {
      const nextButton = document.getElementById('nextButton');
      const backButton = document.getElementById('backButton');

      if (nextButton) {
        nextButton.removeEventListener('click', () => {
          setNav(nav + 1);
        });
      }

      if (backButton) {
        backButton.removeEventListener('click', () => {
          setNav(nav - 1);
        });
      }
    };
  }, [nav]);

  return (
    <div id="cal-page">
      <div id="container">
        <div id="header">
          <div className="nav-container">
            <div className="container">
              <div id="button">
                <button
                  className="edit-button"
                  id="addButton"
                  onClick={handleCreate}
                >
                  Schedule Vacation
                </button>
                <button className="edit-button" id="updateButton">
                  Update Vacation
                </button>
                {/*<button className="edit-button" id="cancelButton">
                  Cancel Vacation
                </button>
                */}
                <button className="edit-button" onClick={handleReports}>
                  Vacation Reports
                </button>
              </div>
            </div>
          </div>

          <div id="monthDisplay"></div>
          <div>
            <button className="cal-button" id="backButton">
              Back
            </button>
            <button className="cal-button" id="nextButton">
              Next
            </button>
            <button className="logout-button" id="logoutButton">
              Log Out
            </button>
          </div>
        </div>

        <div id="weekdays">
          <div>Sunday</div>
          <div>Monday</div>
          <div>Tuesday</div>
          <div>Wednesday</div>
          <div>Thursday</div>
          <div>Friday</div>
          <div>Saturday</div>
        </div>

        <div id="calendar"></div>
      </div>
    </div>
  );
}
