var onDeleteUserClick = function (id) {
    var deleteRequest = $.ajax({
        url: "../api/Users?id=" + id,
        type: "delete",
        contentType: "application/json"
    });

    deleteRequest.done(function () {
        $('#rowForUser_' + id).remove();
    });

    deleteRequest.fail(function (jqXHR, textStatus, errorThrown) {
        alert("The following error occured: " + textStatus + "plain:" + errorThrown);
    });
};

var updateUsersGrid = function () {
    var getAllRequest = $.ajax({
        url: "../api/Users",
        type: "get",
        contentType: "application/json"
    });

    getAllRequest.done(function (response) {
        var r = [], j = -1, size = response.items.length, i, item;
        for (i = 0; i < size; i++) {
            item = response.items[i];
            r[++j] = '<tr id=\'rowForUser_' + item.id + '\'><td>';
            r[++j] = item.id;
            r[++j] = '</td><td>';
            r[++j] = item.firstName;
            r[++j] = '</td><td>';
            r[++j] = item.lastName;
            r[++j] = '</td><td>';
            r[++j] = item.email;
            r[++j] = "</td><td><button class='btn btn-danger btn-xs' onclick='onDeleteUserClick\(";
            r[++j] = item.id;
            r[++j] = '\)\'>Delete</button></td></tr>';
        }
        $('#usersTableBody').html(r.join(''));
    });
};

$(function () {
    var request;

    updateUsersGrid();

    $("#userCreateForm").submit(function (event) {
        var $form, $inputs, serializedData, jsonObject;

        if (request) {
            request.abort();
        }
        $form = $(this);
        $inputs = $form.find("input, select, button, textarea");
        serializedData = $form.serializeArray();

        jsonObject = {};
        serializedData.forEach(function (item) {
            jsonObject[item.name] = item.value;
        });

        $inputs.prop("disabled", true);

        request = $.ajax({
            url: "../api/Users",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(jsonObject)
        });

        request.done(function () {
            document.forms["userCreateForm"].reset();
            updateUsersGrid();
        });

        request.fail(function (jqXHR, textStatus, errorThrown) {
            alert("The following error occured: " + textStatus + "plain:" + errorThrown);
        });

        request.always(function () {
            $inputs.prop("disabled", false);
        });

        event.preventDefault();
    });
});