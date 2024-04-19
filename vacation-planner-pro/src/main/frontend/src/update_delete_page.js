document.addEventListener('DOMContentLoaded', function() {
    fetchVacations();
});

function fetchVacations() {
    const simulatedResponse = [
        { id: '1', startDate: '2024-04-10', endDate: '2024-04-20', description: 'Sick Leave' },
        { id: '2', startDate: '2024-06-15', endDate: '2024-06-25', description: 'Vacation' },
        { id: '3', startDate: '2024-12-20', endDate: '2025-01-05', description: 'Maternity Leave' }
    ];

    const vacationListElement = document.getElementById('vacationList');
    vacationListElement.innerHTML = ''; // Clear existing entries
    simulatedResponse.forEach(vacation => {
        vacationListElement.innerHTML += `
            <div class="vacation-entry">
                <p><strong>${vacation.description}</strong> from ${vacation.startDate} to ${vacation.endDate}</p>
                <button onclick="editVacation('${vacation.id}')" class="edit-button">Edit</button>
                <button onclick="deleteVacation('${vacation.id}')" class="delete-button">Delete</button>
            </div>
        `;
    });
}

function editVacation(vacationId) {
    console.log(`Editing vacation ${vacationId}`);
    // Placeholder for actual edit functionality
}

function deleteVacation(vacationId) {
    console.log(`Deleting vacation ${vacationId}`);
    // Placeholder for actual delete functionality
}


/* 
function fetchVacations() {
    fetch('/vacations') // Adjust this URL to match with backend endpoint
        .then(response => response.json())
        .then(vacations => {
            const vacationListElement = document.getElementById('vacationList');
            vacationListElement.innerHTML = ''; // Clear existing entries
            vacations.forEach(vacation => {
                vacationListElement.innerHTML += `
                    <div class="vacation-entry">
                        <p>Vacation from ${vacation.startDate} to ${vacation.endDate}</p>
                        <button onclick="editVacation('${vacation.id}')">Edit</button>
                        <button onclick="deleteVacation('${vacation.id}')">Delete</button>
                    </div>
                `;
            });
        });
}

function editVacation(vacationId) {
    // Placeholder for edit functionality
    // This could open a modal or form where the user can submit changes
    console.log(`Editing vacation ${vacationId}`);
    // After editing, might call fetchVacations() again to refresh the list
}

function deleteVacation(vacationId) {
    if(confirm('Are you sure you want to delete this vacation?')) {
        fetch(`/vacations/${vacationId}`, { method: 'DELETE' })
            .then(response => {
                if(response.ok) {
                    alert('Vacation deleted successfully');
                    fetchVacations(); // Refresh the list after deletion
                } else {
                    alert('There was a problem deleting the vacation.');
                }
            });
    }
}
*/