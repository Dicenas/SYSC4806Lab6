$(document).ready(function() {
    const addressBookId = window.location.href.split("=").pop();

    function loadBuddies() {
        $.ajax({
            type: "GET",
            url: `/showaddressbook/api/${addressBookId}`,
            dataType: "json",
            success: function(data) {
                const tableBody = $("#buddy-table");
                tableBody.empty();

                data.buddies.forEach(buddy => {
                    const row = `<tr>
                                    <td>${buddy.id}</td>
                                    <td>${buddy.name}</td>
                                    <td>${buddy.phoneNumber}</td>
                                    <td>${buddy.address}</td>
                                 </tr>`;
                    tableBody.append(row);
                });
            },
            error: function(error) {
                console.error("Error loading buddies:", error);
            }
        });
    }

    loadBuddies();

    $('#submitBtn').click(function(event) {
        event.preventDefault();

        const formData = {
            addressBookId: $('input[name="addressBookId"]').val(),
            buddy: {
                name: $('input[name="buddy.name"]').val(),
                phoneNumber: $('input[name="buddy.phoneNumber"]').val(),
                address: $('input[name="buddy.address"]').val()
            }
        };

        $.ajax({
            url: '/createbuddy',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                window.location.href = "/addressbook";
            },
            error: function(error) {
                console.error(error);
            }
        });
    });

    $('#deleteBuddyButton').click(function() {
        const buddyId = $('input[name="buddyId"]').val();
        const addressBookId = $('input[name="addressBookId"]').val();

        $.ajax({
            type: 'POST',
            url: '/deletebuddy',
            data: JSON.stringify({ buddyId: buddyId, addressBookId: addressBookId }),
            contentType: 'application/json',
            success: function(response) {
                window.location.href = "/addressbook";
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});
