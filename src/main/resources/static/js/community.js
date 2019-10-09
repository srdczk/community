function postComment() {
    var parentId = $("#parentId").val();
    var text = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            "parentId" : parentId,
            "text" : text,
            "type" : 1
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