$(document).ready(function () {
    $(".overview").each(function () {
        $(this).animate({height: $(this).attr("height")}, 150);
    });

    function block(e) {
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: window.location.href + "/block",

            success: function (data) {
                console.log(data);
                $(".active_action_block").empty();
                $(".active_action_block").append("<span class=\"unblock_user\">Разблокировать</span>");
                $(".unblock_user").on("click", unblock);
            }
        });
    }

    function unblock(e) {
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: window.location.href + "/unblock",

            success: function (data) {
                console.log(data);
                $(".active_action_block").empty();
                $(".active_action_block").append("<span class=\"block_user\">Заблокировать</span>");
                $(".block_user").on("click", block);
            }
        });
    }

    $(".block_user").on("click", block);
    $(".unblock_user").on("click", unblock);

});




