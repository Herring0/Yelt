$(document).ready(function () {
    $(".overview").each(function() {
        $(this).animate({height: $(this).attr("height")}, 150);
    });
});