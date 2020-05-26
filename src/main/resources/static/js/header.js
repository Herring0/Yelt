$(document).ready(function(){
     $(".search_button").mouseover(function(){
         $(".search_input").css("border", "2px solid var(--terra_cotta)");
     });

     $(".search_button").mouseleave(function() {
     	$(".search_input").css("border", "2px solid #1D222850");
     })

    var search = async function (e) {
         $(".lds-dual-ring").css("visibility", "visible");
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            url: "/search",
            data: "query=" + $(".search_input").val(),

            success: function (data) {
                if (!$.isEmptyObject(data)) {
                    console.log(data);
                    $(".search_result_container").empty();
                    if (data.results.length == 0) {
                        $(".search_result_container").empty();
                        $(".search_result_container").append('<li class=\'empty_result\'><a class="search_result_link_empty">No results</a></li>');
                    } else {
                        for (var i = 0; i < data.results.length; i++) {
                            if (i == 10) break;
                            var id = data.results[i].id;
                            var title = data.results[i].title;
                            // $(".search_result_container").append('<li class=\'search_result\'><a href=\'/movie/' + id + '\'>' + title + '</a></li>');
                            $(".search_result_container").append('\
                                <li class="search_result">\
                                    <a href=\'/movie/' + id + '\' class="search_result_link">\
                                        <div class="lds-dual-ring"></div>\
                                    ' + title + '\
                                    </a>\
                                </li>\
                            ');
                        }
                    }
                }
                $(".lds-dual-ring").css("visibility", "hidden");
            }
        });
    }

    $(".search_input").keyup(search);
});
