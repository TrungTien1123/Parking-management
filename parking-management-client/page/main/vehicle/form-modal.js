$(function () {
    $('#form-modal-btn-create').on('click', function (event) {
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/api/vehicle',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                name: $('#form-name').val(),
                price: $('#form-price').val(),
                salePrice: $('#form-sale-price').val(),
                thumbnailUrl: $('#form-thumbnail-url').val(),
                description: $('#form-description').val(),
                ram: $('#form-ram').val(),
                buildingId: $('#form-building').val()
            }),
            success: function (data) {
                loadProducts();
                $('#product-form').trigger("reset");
            }
        });
    });

    $('#form-modal-btn-update').on('click', function (event) {
        const id = $('#form-id').val();
        $.ajax({
            method: 'PUT',
            url: 'http://localhost:8080/api/vehicle/' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                id: id,
                name: $('#form-name').val(),
                price: $('#form-price').val(),
                salePrice: $('#form-sale-price').val(),
                thumbnailUrl: $('#form-thumbnail-url').val(),
                description: $('#form-description').val(),
                ram: $('#form-ram').val(),
                buildingId: $('#form-building').val()
            }),
            success: function (data) {
                loadProducts();
                $('#product-form').trigger("reset");
                bootstrap.Modal.getOrCreateInstance($('#form-modal')).hide();
            }
        });
    });
});