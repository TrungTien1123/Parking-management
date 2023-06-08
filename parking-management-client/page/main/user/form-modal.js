$(function () {
    $('#form-modal-btn-create').on('click', function (event) {
        // $('#form-student-id-container').hide();
        // $('#form-modal-btn-create').on('change', function (event) {
        // if($('#form-role').val() =='STUDENT'){
        //     $('#form-student-id-container').show();
        // }
    // });
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/api/user',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                username: $('#form-username').val(),
                firstName: $('#form-first-name').val(),
                lastName: $('#form-last-name').val(),
                password: $('#form-password').val(),
                role: $('#form-role').val(),
                buildingId: $('#form-building-id').val(),
                studentId:$('#form-student-id').val()
            }),
            success: function (data) {
                loadUsers();
                $('#user-form').trigger("reset");
            }
        });
    });

    $('#form-modal-btn-update').on('click', function (event) {
        const id = $('#form-id').val();
        $.ajax({
            method: 'PUT',
            url: 'http://localhost:8080/api/user' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                id: id,
                username: $('#form-username').val(),
                firstName: $('#form-first-name').val(),
                lastName: $('#form-last-name').val(),
                password: $('#form-password').val(),
                role: $('#form-role').val(),
                buildingId: $('#form-building-id').val(),
            }),
            success: function (data) {
                loadUsers();
                $('#user-form').trigger("reset");
                bootstrap.Modal.getOrCreateInstance($('#form-modal')).hide();
            }
        });
    });
});