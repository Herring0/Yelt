$(document).ready(function(){
     $(".search_button").mouseover(function(){
         $(".search_input").css("border", "2px solid var(--terra_cotta)");
     });

     $(".search_button").mouseleave(function() {
     	$(".search_input").css("border", "2px solid #1D222850");
     })

    var search = function (e) {
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: "/search",
            data: "query=" + $(".search_input").val(),

            success: function (data) {
                if (!$.isEmptyObject(data)) {
                    console.log(data);
                    $(".search_result_container").empty();
                    for (var i = 0; i < data.results.length; i++) {
                        if (i == 10) break;
                        var id = data.results[i].id;
                        var title = data.results[i].title;
                        $(".search_result_container").append('<li class=\'search_result\'><a href=\'/movie/' + id +'\'>' + title + '</a></li>');
                    }

                }
            }
        });
    }

    $(".search_input").keyup(search);
});