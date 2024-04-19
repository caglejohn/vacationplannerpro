document.addEventListener('DOMContentLoaded', function() {

    generateWeekCalendar();
});

function generateWeekCalendar() {
    const weekDays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    const weekCalendar = document.getElementById('week-calendar');
    const today = new Date();
    const currentDayOfWeek = today.getDay(); // Sunday - 0, Monday - 1, etc.

    // Clear previous content
    weekCalendar.innerHTML = '';

    // Create the row for weekdays
    const weekDaysRow = document.createElement('div');
    weekDaysRow.className = 'week-days-row';
    weekCalendar.appendChild(weekDaysRow); // Add the row for days of the week

    // Create the row for dates
    const datesRow = document.createElement('div');
    datesRow.className = 'dates-row';
    weekCalendar.appendChild(datesRow); // Add the row for dates

    // Calculate the date of the previous Sunday
    today.setDate(today.getDate() - currentDayOfWeek);

    // Generate the week view
    for (let i = 0; i < 7; i++) {
        // Create day element for day names
        const dayDiv = document.createElement('div');
        dayDiv.className = 'day';
        dayDiv.textContent = weekDays[i];
        weekDaysRow.appendChild(dayDiv); // Append to the days row

        // Create date element for dates
        const dateDiv = document.createElement('div');
        dateDiv.className = 'date';
        dateDiv.textContent = today.getDate();
        datesRow.appendChild(dateDiv); // Append to the dates row

        today.setDate(today.getDate() + 1); // Increment date
    }
}



document.getElementById('timeOffForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form from submitting immediately

    //  validation
    var leaveType = document.getElementById('leaveType').value;
    if (!leaveType) {
        alert('Please select a leave type.');
        return;
    }

    alert('Time off request submitted successfully!');

    // Reset the form or redirect the user as needed
    // this.reset();
    // window.location.href = 'confirmationPage.html'; // redirect
});

