var api = '/api/comments';
var $commentsSubmitButton;
var $ajaxLoader;
var $commentsTable;
var $commentInput;

$(document).ready(function () {
    $commentsSubmitButton = $('#submitComment');
    $ajaxLoader = $('#ajaxLoader');
    $commentsTable = $('#commentsTable');
    $commentInput = $('#body');

    $commentsSubmitButton.click(function (evt) {
        evt.preventDefault();
        addComment();
    });

    updateComments();
});

function addComment() {
    $commentsSubmitButton.attr("disabled", true);
    var JSONObject = {
        'id': newsId,
        'body': $commentInput.val()
    };
    $.ajax({
        type: 'POST',
        url: api,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        success: function () {
            $commentInput.val('');
            $commentInput.focus();
            updateComments();
        },
        complete: function () {
            $commentsSubmitButton.attr("disabled", false);
        }
    });
}


function updateComments() {
    $commentsTable.html('');
    $ajaxLoader.show();
    $.ajax({
        type: 'GET',
        url: api +"/" + newsId,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (message) {
            updateCommentsTable(message.body);
        },
        complete: function () {
            $ajaxLoader.hide();
        }
    });
}

function updateCommentsTable(comments) {
    var commentsHtml = '';
    comments.forEach(function (comment) {
        commentsHtml += '<tr><td>' + comment + '</td></tr>';
    });
    $commentsTable.html(commentsHtml);
}