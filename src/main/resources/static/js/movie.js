$(document).ready(function () {
    var childs = document.getElementsByClassName("star");
    for (let child of childs) {
        child.onmouseover = function () {
            var parent = child.parentNode;
            var children = parent.children;
            var i = children.length - 1;
            var ind;
            for (; i >= 0; i--) {
                if (child == children[i]) {
                    ind = i + 1;
                    break;
                }
            }

            for (var j = 1; j < ind + 1; j++) {
                $(".star:nth-child(" + j + ")").css("color", "var(--charcoal)");
            }
        };
    }

    $(".star").mouseleave(function () {
        $(".star").css("color", "#e8e5e3");
    })

    var show_video = function (e) {
        var yid = $(e.target).closest(".video").attr("yid");
        var link = "https://www.youtube.com/embed/" + yid + "?autoplay=1";
        $(".i_video").attr("src", link);
        $(".video_show_container").css("display", "flex");
    };

    var hide_video = function (e) {
        $(".video_show_container").css("display", "none");
        $(".i_video").attr("src", "");
    }


    var rate = function (e) {
        var now = new Date();
        var date = now.getFullYear() + '.' + String((now.getMonth() + 1)).padStart(2, '0') + '.' + String(now.getDate()).padStart(2, '0');
        var time = now.getHours() + ":" + String(now.getMinutes()).padStart(2, '0') + ":" + String(now.getSeconds()).padStart(2, '0');

        child = e.target;
        var parent = child.parentNode;
        var children = parent.children;
        var i = children.length - 1;
        var ind;
        for (; i >= 0; i--) {
            if (child == children[i]) {
                ind = i + 1;
                break;
            }
        }

        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: window.location.href,
            data: 'rating=' + ind + "&date=" + date + " " + time,

            success: function (data) {
                if (!$.isEmptyObject(data)) {
                    $(".my_vote_block").removeClass("hidden");
                    $(".my_vote").text(data.rating);
                    $(".date_vote").text(data.date);
                    document.location.reload(true);
                }
            }
        });
    }

    var delete_vote = function (e) {
        var now = new Date();
        var date = now.getFullYear() + '.' + String((now.getMonth() + 1)).padStart(2, '0') + '.' + String(now.getDate()).padStart(2, '0');
        var time = now.getHours() + ":" + String(now.getMinutes()).padStart(2, '0') + ":" + String(now.getSeconds()).padStart(2, '0');

        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: window.location.href,
            data: 'rating=' + (-1) + "&date=" + date + " " + time,

            success: function (data) {
                $(".my_vote_block").addClass("hidden");
                document.location.reload(true);
            }
        });
    }

    var write_review = function (e) {
        $(".write_review_container").css("display", "flex");
    }

    var hide_review = function (e) {
        $(".write_review_container").css("display", "none");
    }

    var delete_review = function (e) {
        console.log("was send");
        var that = this;
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: window.location.href + "/delete_review",

            success: function (data) {
                console.log("success");
                $(that).closest(".review").animate({opacity: 0, height: "0px", padding: "0px"}, 500, function () {
                    $(that).closest(".review").css("display", "none");
                });
            }
        });
    }

    $(".review_footer").click(function (e) {
        var height = $(e.target).parent().children(".review_main").children(".review_text").height() + 5;
        if (height > 410) {
            if ($(this).hasClass("collapsed")) {
                $(e.target).parent().children(".review_main").animate({height: height.toString()}, 1000);
                $(e.target).parent().children(".review_footer").children(".review_footer_icon").removeClass("rotate0");
                $(e.target).parent().children(".review_footer").children(".review_footer_icon").addClass("rotate180");
                $(this).removeClass("collapsed");
            } else {
                $(e.target).parent().children(".review_main").animate({height: '400px'}, 1000);
                $(e.target).parent().children(".review_footer").children(".review_footer_icon").removeClass("rotate180");
                $(e.target).parent().children(".review_footer").children(".review_footer_icon").addClass("rotate0");
                $(this).addClass("collapsed");
            }
        }
    });


    $(".video img").click(show_video);
    $(".video_btn_container").click(show_video);
    $(".i_video_close").click(hide_video);
    $(".video_show_container").click(hide_video);
    $(".star").click(rate);
    $(".delete_vote").click(delete_vote);
    $(".write_review").click(write_review);
    $(".i_review_close").click(hide_review);
    $(".review_delete_block").click(delete_review);
});


