// Admin Script for managing Rooms and Bookings

document.addEventListener("DOMContentLoaded", function () {
    // Function to show a confirmation dialog before deleting a room
    function confirmDeleteRoom(roomId) {
        if (confirm("Are you sure you want to delete this room?")) {
            window.location.href = '/admin/delete-room/' + roomId;
        }
    }

    // Function to handle room editing (populate form fields with room data)
    function editRoom(roomId) {
        fetch('/admin/get-room/' + roomId)
            .then(response => response.json())
            .then(data => {
                // Assuming you have an HTML form with fields for room data
                document.getElementById('editRoomId').value = data.id;
                document.getElementById('editRoomNumber').value = data.roomNumber;
                document.getElementById('editRoomType').value = data.roomType;
                document.getElementById('editRoomPrice').value = data.price;
                document.getElementById('editRoomAvailability').checked = data.isAvailable;
                // Show the edit form (assuming it's hidden by default)
                document.getElementById('editRoomForm').style.display = 'block';
            })
            .catch(error => console.error('Error fetching room data:', error));
    }

    // Example event listener for a room delete button
    const deleteButtons = document.querySelectorAll('.delete-room-btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const roomId = this.getAttribute('data-room-id');
            confirmDeleteRoom(roomId);
        });
    });

    // Example event listener for a room edit button
    const editButtons = document.querySelectorAll('.edit-room-btn');
    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const roomId = this.getAttribute('data-room-id');
            editRoom(roomId);
        });
    });
});
