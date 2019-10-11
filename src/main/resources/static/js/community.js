function sendComment(parentId, text, type) {
    if (!text) alert('输入内容不能为空!');
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            "parentId" : parentId,
            "text" : text,
            "type" : type
        }),
        dataType: "json",
        success:function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                alert(response.message);
            }
        }
    });
}


function postComment() {
    var parentId = $("#parentId").val();
    var text = $("#comment_content").val();
    sendComment(parentId, text, 1);
}

function comment(e) {
    var parentId = e.getAttribute('data-id');
    var text = $("#input-" + parentId).val();
    console.log(parentId + ' ' + text);
    sendComment(parentId, text, 2);
}

function showTags() {
    $("#select-tag").show();
}

function selectTag(e) {

    var tag = e.getAttribute('data-tag');
    var tags = $("#labels").val();

    if (tags.length == 0) {
        $("#labels").val(tag);
    } else {
        var index = tags.lastIndexOf(tag);
        if (index == -1) {
            $("#labels").val(tags + ',' + tag);
        } else {
            if ((index + tag.length) == tags.length) return;
            if (tags[index + tag.length] == ',') return;
            $("#labels").val(tags + ',' + tag);
        }
    }


}
