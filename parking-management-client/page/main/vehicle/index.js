var KEY_ENTER = 13;
var sort = null;

$(function () {
    $('#form-modal-container').load('/page/main/vehicle/form-modal.html');
    $('#delete-modal-container').load('/common/modal/delete-modal.html', null, function () {
        $('#delete-modal-btn-remove').on('click', function (event) {
            const ids = $('.selected .id').toArray()
            for(let i=0;i<ids.length;i++){
                id = ids[i].innerText;
            $.ajax({
                method: 'DELETE',
                url: 'http://localhost:8080/api/vehicle/'+id,
                beforeSend: () => showLoading(),
                success: data => loadVehicles(),
                complete: () => hideLoading()
            });}
            bootstrap.Modal.getOrCreateInstance($('#delete-modal')).hide();
        });
    });

    if (storage.getItem('key_role') != 'ADMIN') {
        $('#btn-add').hide();
        $('#btn-edit').hide();
        $('#btn-delete').hide();
    }

    addListeners();
    loadBuilding();
    loadVehicles();
});

function addListeners() {
    $('#btn-search, #btn-refresh').on('click', event => loadVehicles());

    // Khi người dùng thay đổi page size
    $('#page-size').on('change', event => loadVehicles());

    // Khi người dùng thay đổi page number và nhấn ENTER
    $('#page-number').on('keypress', event => {
        if (event.which == KEY_ENTER) {
            loadVehicles();
        }
    });

    $('#vehicle-tbody').on('click', 'tr', function (event) {
        if (event.ctrlKey) {
            $(this).toggleClass('selected');
        } else {
            $(this).addClass('selected').siblings().removeClass('selected');
        }
        updateStatus();
    });

    $('#vehicle-thead').on('click', 'th', function (event) {
        $(this).siblings().find('i').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');

        const i = $(this).find('i');
        if (i.hasClass('fa-sort')) {
            i.removeClass('fa-sort').addClass('fa-sort-up');
        } else {
            i.toggleClass('fa-sort-up fa-sort-down');
        }

        let type = i.hasClass('fa-sort-up') ? 'asc' : 'desc';
        sort = `${$(this).attr('key')},${type}`
        loadVehicles();
    });

    $('#btn-add').on('click', event => {
        $('#vehicle-form').trigger('reset');
        $('#form-id-container').hide();
        $('#form-modal-btn-update').hide();
        $('#form-modal-btn-create').show();
        $('#form-modal-title').text('Thêm sản phẩm');
    });

    $('#btn-edit').on('click', event => {
        $('#vehicle-form').trigger('reset');
        $('#form-modal-btn-create').hide();
        $('#form-modal-btn-update').show();
        $('#form-id-container').show();
        $('#form-modal-title').text('Cập nhật sản phẩm');

        const row = $('.selected');
        $('#form-id').val(row.find('.id').attr('value'));
        $('#form-name').val(row.find('.name').attr('value'));
        $('#form-price').val(row.find('.price').attr('value'));
        $('#form-sale-price').val(row.find('.salePrice').attr('value'));
        $('#form-thumbnail-url').val(row.find('.thumbnailUrl').attr('value'));
        $('#form-description').val(row.find('.description').text());
        $('#form-ram').val(row.find('.ram').attr('value'));
        $('#form-building').val(row.find('.buildingId').attr('value'));
    });

    $('#btn-delete').on('click', function (event) {
        $('#delete-modal-title').text('Xóa sản phẩm');
        const message = `Bạn chắc chắn muốn xóa ${$('.selected').length} sản phẩm?`;
        $('#delete-modal-body').text(message);
    });
}

function updateStatus() {
    const length = $('.selected').length;
    if (length == 0) {
        $('#btn-edit').attr('disabled', 'disabled');
        $('#btn-delete').attr('disabled', 'disabled');
    } else if (length == 1) {
        $('#btn-edit').removeAttr('disabled');
        $('#btn-delete').removeAttr('disabled');
    } else {
        $('#btn-edit').attr('disabled', 'disabled');
        $('#btn-delete').removeAttr('disabled');
    }
}

function loadBuilding() {
    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/api/building',
        success: (data) => showBuilding(data.content),
        error: () => location.replace('/common/error/404-not-found.html')
    });
}

function loadVehicles() {
    const searchParams = new URLSearchParams();

    let ram = $('#ram').val();
    if (!ram) ram = null;
    let buildingId = $('#building-filter').val();
    if (!buildingId) buildingId = null;

    const params = {
        page: $('#page-number').val(),
        size: $('#page-size').val(),
        sort: sort,
        search: $('#search').val(),
        ram: ram,
        buildingId: buildingId,
        minYear: $('#min-year').val(),
        maxYear: $('#max-year').val(),
        minCreatedDate: $('#min-created-date').val(),
        maxCreatedDate: $('#max-created-date').val(),
        minSalePrice: $('#min-sale-price').val(),
        maxSalePrice: $('#max-sale-price').val(),
    }
    for (const key in params) {
        if (params[key]) {
            searchParams.set(key, params[key]);
        }
    }

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/api/vehicle?' + searchParams,
        beforeSend: () => showLoading(),
        success: function (data) {
            showPageInfo(data);
            showVehicles(data.content);
            updateStatus();
        },
        // error: () => location.replace('/common/error/404-not-found.html'),
        // complete: () => hideLoading()
    });
}

function showBuilding(buildings) {
    const filter = $('#building-filter');
    const form = $('#form-building');
    let options = '';
    for (const building of buildings) {
        options += `<option value="${building.id}">${building.name}</option>`;
    }
    filter.append(options);
    form.append(options);
}

function showPageInfo(data) {
    const start = data.pageable.offset;
    $('#page-info').text(`Showing ${start + 1} to ${start + data.numberOfElements} of ${data.totalElements} rows.`);
    $('#page-number').attr('max', data.totalPages);
    if (data.last) {
        $('#next').addClass('disabled');
    } else {
        $('#next').removeClass('disabled');
    }
    if (data.first) {
        $('#previous').addClass('disabled');
    } else {
        $('#previous').removeClass('disabled');
    }
}

function showVehicles(vehicles) {
    const tbody = $('#vehicle-tbody');
    tbody.empty();
    for (const vehicle of vehicles) {
        const updatedAt = new Date(vehicle.updatedAt);
        tbody.append(`
            <tr>
                <th class='id' value='${vehicle.id}' scope="row">${vehicle.id}</th>
                <td class='color' value='${vehicle.color}'>${vehicle.color}</td>
                <td class='status'>${vehicle.status}</td>
                <td class='createdDate'>${vehicle.createdDate}</td>
                <td class='updatedAt'>${updatedAt.toLocaleDateString()} ${updatedAt.toLocaleTimeString()}</td>
                <td class='buildingName' value='${vehicle.building.id}'>${vehicle.building.name}</td>
            </tr>
        `);
    }
}

function showLoading() {
    $('#loading').show();
}

function hideLoading() {
    $('#loading').hide();
}

function changePageNumberBy(value) {
    const page = $('#page-number');
    page.val(+page.val() + value);
    loadVehicles();
}
