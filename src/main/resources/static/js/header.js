$(document).ready(function () {
    var timer;
    $(".search_button").mouseover(function () {
        $(".search_input").css("border", "2px solid var(--terra_cotta)");
    });

    $(".search_button").mouseleave(function () {
        $(".search_input").css("border", "2px solid #1D222850");
    })

    var search = async function (e) {
        timer = setTimeout(function () {
            var movieResults;
            var peopleResults;

            $(".lds-dual-ring").css("visibility", "visible");
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                url: "/search/movies",
                data: "query=" + $(".search_input").val(),

                success: function (data) {
                    if (!$.isEmptyObject(data)) {
                        movieResults = data;
                        $.ajax({
                            type: "POST",
                            contentType: "application/x-www-form-urlencoded",
                            url: "/search/people",
                            data: "query=" + $(".search_input").val(),

                            success: function (data) {
                                if (!$.isEmptyObject(data)) {
                                    peopleResults = data;
                                    var finalList = [];

                                    if (movieResults.results.length == 0 && peopleResults.results.length == 0) {
                                        $(".search_result_container").empty();
                                        $(".search_result_container").append('<li class=\'empty_result\'><a class="search_result_link_empty">No results</a></li>');
                                    } else if (movieResults.results.length == 0) {
                                        finalList = peopleResults.results;
                                    } else if (peopleResults.results.length == 0) {
                                        finalList = movieResults.results;
                                    } else {
                                        finalList = movieResults.results;
                                        peopleResults.results.forEach(element => finalList.push(element));
                                        finalList.sort((a, b) => (a.popularity < b.popularity) ? 1 : -1);
                                    }

                                    $(".search_result_container").empty();

                                    for (var i = 0; i < finalList.length; i++) {
                                        if (i == 10) break;
                                        if (finalList[i].dataType == "movie") {
                                            var id = finalList[i].id;
                                            var title = finalList[i].title;
                                            $(".search_result_container").append('\
                                            <li class="search_result">\
                                                <a href=\'/movie/' + id + '\' class="search_result_link">\
                                                    <div class="lds-dual-ring"></div>\
                                                    ' + title + '\
                                                </a>\
                                            </li>\
                                        ');
                                        } else if (finalList[i].dataType == "people") {
                                            var id = finalList[i].id;
                                            var name = finalList[i].name;
                                            $(".search_result_container").append('\
                                            <li class="search_result">\
                                                <a href=\'/people/' + id + '\' class="search_result_link">\
                                                    <div class="lds-dual-ring"></div>\
                                                    ' + name + '\
                                                </a>\
                                            </li>\
                                        ');
                                        }
                                    }
                                }
                                $(".lds-dual-ring").css("visibility", "hidden");
                            }
                        });
                    } else {
                        $(".lds-dual-ring").css("visibility", "hidden");
                    }
                },
            });

        }, 100);
    }

    var didScroll;

    $(".search_input").keyup(search).keydown(function () {
        clearTimeout(timer);
    });
    $(".search_button").click(function () {
        window.location.href = window.location.origin + `search?query=${$(".search_input").val()}`;
    });
});
