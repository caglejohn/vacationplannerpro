window.onload = function () {
  let nav = 0;
  let clicked = null;

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

  function openModal(date) {
    clicked = date;
  }

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

      /*  const dayString = `${month + 1}/${i - paddingDays}/${year}`;*/

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

  function closeModal() {
    clicked = null;
    load();
  }

  function initButtons() {
    document.getElementById('nextButton').addEventListener('click', () => {
      nav++;
      load();
    });

    document.getElementById('backButton').addEventListener('click', () => {
      nav--;
      load();
    });
  }
  initButtons();
  load();
};
