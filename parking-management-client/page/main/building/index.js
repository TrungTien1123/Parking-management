var KEY_ENTER = 13;
var sort = null;

$(function () {
    $('#form-modal-container').load('/page/main/building/form-modal.html');
    $('#delete-modal-container').load('/common/modal/delete-modal.html', null, function () {
        $('#delete-modal-btn-remove').on('click', function (event) {
            const ids = $('.selected .id').toArray()
            for(let i=0;i<ids.length;i++){
                id = ids[i].innerText;
            $.ajax({
                method: 'DELETE',
                url: 'http://localhost:8080/api/building/' + id,
                contentType: 'application/json; charset=utf-8',
                beforeSend: () => showLoading(),
                success: data => loadBuildings(),
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
    loadBuildings();
});

function addListeners() {
    $('#btn-search, #btn-refresh').on('click', event => loadBuildings());

    // Khi người dùng thay đổi page size
    $('#page-size').on('change', event => loadBuildings());

    // Khi người dùng thay đổi page number và nhấn ENTER
    $('#page-number').on('keypress', event => {
        if (event.which == KEY_ENTER) {
            loadBuildings();
        }
    });

    $('#building-tbody').on('click', 'tr', function (event) {
        if (event.ctrlKey) {
            $(this).toggleClass('selected');
        } else {
            $(this).addClass('selected').siblings().removeClass('selected');
        }
        updateStatus();
    });

    $('#building-thead').on('click', 'th', function (event) {
        $(this).siblings().find('i').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');

        const i = $(this).find('i');
        if (i.hasClass('fa-sort')) {
            i.removeClass('fa-sort').addClass('fa-sort-up');
        } else {
            i.toggleClass('fa-sort-up fa-sort-down');
        }

        let type = i.hasClass('fa-sort-up') ? 'asc' : 'desc';
        sort = `${$(this).attr('key')},${type}`
        loadBuildings();
    });

    $('#btn-add').on('click', event => {
        $('#building-form').trigger('reset');
        $('#form-id-container').hide();
        $('#form-modal-btn-update').hide();
        $('#form-modal-btn-create').show();
        $('#form-modal-title').text('Thêm cơ quan');
    });

    $('#btn-edit').on('click', event => {
        $('#building-form').trigger('reset');
        $('#form-modal-btn-create').hide();
        $('#form-modal-btn-update').show();
        $('#form-id-container').show();
        $('#form-modal-title').text('Cập nhật cơ quan');

        const row = $('.selected');
        $('#form-id').val(row.find('.id').attr('value'));
        $('#form-name').val(row.find('.name').attr('value'));
        $('#form-code').val(row.find('.code').attr('value'));
    });

    $('#btn-delete').on('click', function (event) {
        $('#delete-modal-title').text('Xóa cơ quan');
        const message = `Bạn chắc chắn muốn xóa ${$('.selected').length} cơ quan?`;
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

function loadBuildings() {
    const searchParams = new URLSearchParams();

    const params = {
        page: $('#page-number').val(),
        size: $('#page-size').val(),
        sort: sort,
        search: $('#search').val(),
        minCreatedDate: $('#min-created-date').val(),
        maxCreatedDate: $('#max-created-date').val()
    }
    for (const key in params) {
        if (params[key]) {
            searchParams.set(key, params[key]);
        }
    }

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/api/building?' + searchParams,
        beforeSend: () => showLoading(),
        success: function (data) {
            showPageInfo(data);
            showBuildings(data.content);
            updateStatus();
        },
        error: () => location.replace('/common/error/404-not-found.html'),
        complete: () => hideLoading()
    });
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

function showBuildings(buildings) {
    const tbody = $('#building-tbody');
    tbody.empty();
    for (const building of buildings) {
        const updatedAt = new Date(building.lastModifiedDate);
        const createdAt = new Date(building.createdDate);
        tbody.append(`
            <tr>
                <th class='id' value='${building.id}' scope="row">${building.id}</th>
                <td class='code' value='${building.code}'>${building.code}</td>
                <td class='name' value='${building.name}'>${building.name}</td>
                <td class='address' value='${building.address}'>${building.address}</td>
                <td class='createdDate'>${createdAt.toLocaleDateString()}${createdAt.toLocaleTimeString()}</td>
                <td class='updatedAt'>${updatedAt.toLocaleDateString()} ${updatedAt.toLocaleTimeString()}</td>
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
    loadBuildings();
}
