var KEY_ENTER = 13;
var sort = null;

$(function () {
    $('#form-modal-container').load('/page/main/user/form-modal.html');
    $('#delete-modal-container').load('/common/modal/delete-modal.html', null, function () {
        $('#delete-modal-btn-remove').on('click', function (event) {
            const ids = $('.selected .id').toArray()
            for(let i=0;i<ids.length;i++){
                id = ids[i].innerText;
            $.ajax({
                method: 'DELETE',
                url: 'http://localhost:8080/api/user/' +id,
                beforeSend: () => showLoading(),
                success: data => loadUsers(),
                complete: () => hideLoading()
            });}
            bootstrap.Modal.getOrCreateInstance($('#delete-modal')).hide();
        });
    });

    addListeners();
    loadUsers();
});

function addListeners() {
    $('#btn-search, #btn-refresh').on('click', event => loadUsers());

    // Khi người dùng thay đổi page size
    $('#page-size').on('change', event => loadUsers());

    // Khi người dùng thay đổi page number và nhấn ENTER
    $('#page-number').on('keypress', event => {
        if (event.which == KEY_ENTER) {
            loadUsers();
        }
    });

    $('#user-tbody').on('click', 'tr', function (event) {
        if (event.ctrlKey) {
            $(this).toggleClass('selected');
        } else {
            $(this).addClass('selected').siblings().removeClass('selected');
        }
        updateStatus();
    });

    $('#user-thead').on('click', 'th', function (event) {
        $(this).siblings().find('i').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');

        const i = $(this).find('i');
        if (i.hasClass('fa-sort')) {
            i.removeClass('fa-sort').addClass('fa-sort-up');
        } else {
            i.toggleClass('fa-sort-up fa-sort-down');
        }

        let type = i.hasClass('fa-sort-up') ? 'asc' : 'desc';
        sort = `${$(this).attr('key')},${type}`
        loadUsers();
    });

    $('#btn-add').on('click', event => {
        $('#user-form').trigger('reset');
        $('#form-id-container').hide();
        $('#form-modal-btn-update').hide();
        $('#form-modal-btn-create').show();
        $('#form-modal-title').text('Thêm tài khoản');
    });

    $('#btn-edit').on('click', event => {
        $('#user-form').trigger('reset');
        $('#form-modal-btn-create').hide();
        $('#form-modal-btn-update').show();
        $('#form-id-container').show();
        $('#form-modal-title').text('Cập nhật tài khoản');

        const row = $('.selected');
        $('#form-id').val(row.find('.id').text());
        $('#form-last-name').val(row.find('.lastName').text());
        $('#form-first-name').val(row.find('.firstName').text());
        $('#form-username').val(row.find('.username').text());
        $('#form-role').val(row.find('.role').text());
    });

    $('#btn-delete').on('click', function (event) {
        $('#delete-modal-title').text('Xóa tài khoản');
        const message = `Bạn chắc chắn muốn xóa ${$('.selected').length} tài khoản?`;
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

function loadUsers() {
    const searchParams = new URLSearchParams();

    let role = $('#role').val();
    if (!role) role = null;

    const params = {
        page: $('#page-number').val(),
        size: $('#page-size').val(),
        sort: sort,
        search: $('#search').val(),
        role: role,
        minId: $('#min-id').val(),
        maxId: $('#max-id').val()
    }
    for (const key in params) {
        if (params[key]) {
            searchParams.set(key, params[key]);
        }
    }

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/api/user?' + searchParams,
        beforeSend: () => showLoading(),
        success: function (data) {
            showPageInfo(data);
            showUsers(data.content);
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

function showUsers(users) {
    const tbody = $('#user-tbody');
    tbody.empty();
    for (const user of users) {
        const updatedAt = new Date(user.updatedAt);
        const createdDate = new Date(user.createdDate)
        tbody.append(`
            <tr>
                <th class='id' scope="row">${user.id}</th>
                <td class='lastName''>${user.lastName}</td>
                <td class='firstName'>${user.firstName}</td>
                <td class='username'>${user.username}</td>
                <td class='createdDate'>${createdDate.toLocaleDateString()} ${createdDate.toLocaleTimeString()}</td>
                <td class='updatedAt'>${updatedAt.toLocaleDateString()} ${updatedAt.toLocaleTimeString()}</td>
                <td class='role'>${user.role}</td>
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
    loadUsers();
}
