$(document).ready(function () {
    function delete_vote() {
        var now = new Date();
        var date = now.getFullYear() + '.' + String((now.getMonth() + 1)).padStart(2, '0') + '.' + String(now.getDate()).padStart(2, '0');
        var time = now.getHours() + ":" + String(now.getMinutes()).padStart(2, '0') + ":" + String(now.getSeconds()).padStart(2, '0');
        var that = $(this);
        var count = parseInt($(".count").text());
        var id = that.attr("movie_id");

        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: "/movie/" + id,
            data: 'rating=' + (-1) + "&date=" + date + " " + time,

            success: function (data) {
                $(that).closest(".vote_block").animate({opacity: 0, height: "0px", padding: "0px"}, 500, function () {
                    $(that).closest(".vote_block").css("display", "none");
                    count -= 1;
                    $(".count").text(count);
                });
            }
        });
    }

    function delete_watchlist() {
        var that = $(this);
        var count = parseInt($(".count").text());
        var id = that.attr("movie_id");

        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: "/movie/" + id + "/delete_from_watchlist",

            success: function (data) {
                $(that).closest(".vote_block").animate({opacity: 0, height: "0px", padding: "0px"}, 500, function () {
                    $(that).closest(".vote_block").css("display", "none");
                    count -= 1;
                    $(".count").text(count);
                });
            }
        });
    }

    $(".delete_vote").click(delete_vote);
    $(".delete_wathclist").click(delete_watchlist);
});