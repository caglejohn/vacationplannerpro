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
