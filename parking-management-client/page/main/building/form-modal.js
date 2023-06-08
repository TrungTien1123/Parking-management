$(function () {
    $('#form-modal-btn-create').on('click', function (event) {
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/api/building',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                name: $('#form-name').val(),
                code: $('#form-code').val()

            }),
            success: function (data) {
                loadBuildings();
                $('#building-form').trigger("reset");
            }
        });
    });

    $('#form-modal-btn-update').on('click', function (event) {
        const id = $('#form-id').val();
        $.ajax({
            method: 'PUT',
            url: 'http://localhost:8080/api/building/' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                id: id,
                name: $('#form-name').val(),
                code: $('#form-code').val()
            }),
            success: function (data) {
                loadBuildings();
                $('#building-form').trigger("reset");
                bootstrap.Modal.getOrCreateInstance($('#form-modal')).hide();
            }
        });
    });
});