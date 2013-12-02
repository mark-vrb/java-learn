var onDeleteClick = function (id) {
    var deleteRequest = $.ajax({
        url: "../api/WorkItems?id=" + id,
        type: "delete",
        contentType: "application/json"
    });

    deleteRequest.done(function () {
        $('#row_' + id).remove();
    });

    deleteRequest.fail(function (jqXHR, textStatus, errorThrown) {
        alert("The following error occured: " + textStatus + "plain:" + errorThrown);
    });
};

var updateGrid = function () {
    var getAllRequest = $.ajax({
        url: "../api/WorkItems",
        type: "get",
        contentType: "application/json"
    });

    getAllRequest.done(function (response) {
        var r = [], j = -1, size = response.items.length, i, item;
        for (i = 0; i < size; i++) {
            item = response.items[i];
            r[++j] = '<tr id=\'row_' + item.id + '\'><td>';
            r[++j] = item.id;
            r[++j] = '</td><td>';
            r[++j] = item.name;
            r[++j] = '</td><td>';
            r[++j] = item.description;
            r[++j] = '</td><td>';
            r[++j] = item.creationDate;
            r[++j] = '</td><td>';
            r[++j] = item.createdByUserId;
            r[++j] = '</td><td>';
            r[++j] = item.assigneeUserId;
            r[++j] = '</td><td>';
            r[++j] = item.size;
            r[++j] = '</td><td>';
            r[++j] = item.type;
            r[++j] = '</td><td>';
            r[++j] = item.boardId;
            r[++j] = '</td><td>';
            r[++j] = item.boardColumnId;
            r[++j] = "</td><td><button class='btn btn-danger btn-xs' onclick='onDeleteClick\(";
            r[++j] = item.id;
            r[++j] = '\)\'>Delete</button></td></tr>';
        }
        $('#gridBody').html(r.join(''));
    });
};

$(function () {
    var request;

    updateGrid();

    $("#createForm").submit(function (event) {
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
            url: "../api/WorkItems",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(jsonObject)
        });

        request.done(function () {
            document.forms["createForm"].reset();
            updateGrid();
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