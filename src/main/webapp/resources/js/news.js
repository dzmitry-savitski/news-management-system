var api = contextPath + '/api/news';
var $newsSubmitButton;
var $alert;
var $alertBody;
var $ajaxLoader;

$(document).ready(function () {
    $newsSubmitButton = $('#newsSubmitButton');
    $alert = $('#alert');
    $alertBody = $('#alertBody');
    $ajaxLoader = $('#ajaxLoader');


    $newsSubmitButton.click(function (evt) {
        evt.preventDefault();
        saveNews();
    });

    $('#alertClose').click(function (evt) {
        evt.preventDefault();
        hideMessage();
    });
});

function saveNews() {
    $newsSubmitButton.attr("disabled", true);
    $ajaxLoader.show();
    var JSONObject = {
        'id': $('#newsId').val(),
        'title': $('#title').val(),
        'date': $('#date').val(),
        'body': $('#body').val()
    };
    $.ajax({
        type: 'POST',
        url: api,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        success: function (message) {
            showSuccessMessage(message.body);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var error = JSON.parse(jqXHR.responseText);
            showErrorMessage(error.body);
        },
        complete: function () {
            $ajaxLoader.hide();
            $newsSubmitButton.attr("disabled", false);
        }
    });
}

function showSuccessMessage(message) {
    $alertBody.html(message);
    $alert.removeClass();
    $alert.addClass('alert alert-success')
    $alert.show();
}

function showErrorMessage(message) {
    $alertBody.html(message);
    $alert.removeClass();
    $alert.addClass('alert alert-danger')
    $alert.show();
}

function hideMessage() {
    $alert.hide();
}