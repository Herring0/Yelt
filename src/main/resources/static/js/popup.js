$(document).ready(function () {
    function popupMovie() {

    }

    $(".movie_popup").mouseover(function () {
        var that = this;
        timer = setTimeout(function () {
            var id = $(that).attr("href").split("/")[2];
            console.log("id = " + id);
            $.ajax({
                contentType: "application/x-www-form-urlencoded",
                url: "/api/movie",
                data: 'id=' + id,

                success: function (data) {
                    if (!$.isEmptyObject(data)) {
                        console.log(data);
                    }
                }
            });
        }, 500);
    }).mouseleave(function () {
        clearTimeout(timer);
    });
});