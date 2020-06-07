$(document).ready(function () {
    const queryString = window.location.search;
    if (queryString.length > 0) {
        const urlParams = new URLSearchParams(queryString);
        const query = urlParams.get("query");
        $(".search_panel_input").val(query);
        search();
    }
    var timer;
    async function search() {
        timer = setTimeout(function () {
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });

            if ($("#movie").hasClass("active")) {
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "/search/movies",
                    data: "query=" + $(".search_panel_input").val(),

                    success: async function (data) {
                        if (!$.isEmptyObject(data)) {
                            $(".search_results_container").empty();
                            data.results.forEach((item) => {
                                $(".search_results_container").append(`
                        <a href="/movie/${item.id}" class="movie_search_result_container">\n
                            <img src="https://image.tmdb.org/t/p/w94_and_h141_bestv2${item.poster_path}" alt="" class="poster" onerror="this.src='../../resources/lost_poster.png'">\n
                             <div class="info_container">\n
                                 <div class="title">\n
                                     <span class="movie_title">${item.title}</span>\n
                                     <span class="movie_date">${item.release_date}</span>\n
                                 </div>\n
                                 <span class="overview">${item.overview.length >= 235 ? item.overview.slice(0, 235).concat("...") : item.overview}</span>\n
                             </div>\n
                         </a>`);
                            });
                        }
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "/search/people",
                    data: "query=" + $(".search_panel_input").val(),

                    success: async function (data) {
                        if (!$.isEmptyObject(data)) {
                            $(".search_results_container").empty();
                            data.results.forEach((item) => {
                                $(".search_results_container").append(`
                        <a href="/people/${item.id}" class="movie_search_result_container">\n
                            <img src="https://image.tmdb.org/t/p/w94_and_h141_bestv2${item.profile_path}" alt="" class="poster" onerror="this.src='../../resources/person_large.png'">\n
                             <div class="info_container">\n
                                 <div class="title">\n
                                     <span class="movie_title">${item.name}</span>\n
                                     <span class="movie_date">${item.department}</span>\n
                                 </div>\n
                                 <span class="overview">${item.biography.length >= 235 ? item.biography.slice(0, 235).replace(/\n/g, "").concat("...") : item.biography.replace(/\n/g, "")}</span>\\n
                             </div>\n
                         </a>`);
                            });
                        }
                        $('.info_container').contents().filter(function(){
                            return this.nodeType === 3;
                        }).remove();
                    }
                });
            }
        }, 250);

    }

    function changeToPeople(e) {
        if (!$("#people").hasClass("active")) {
            // $(".search_results_container").empty();
            $("#movie").removeClass("active");
            $("#people").addClass("active");
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
            search();
        }
    }

    function changeToMovie(e) {
        if (!$("#movie").hasClass("active")) {
            // $(".search_results_container").empty();
            $("#people").removeClass("active");
            $("#movie").addClass("active");
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
            search();
        }
    }

    $(window).scroll(function() {
        var pos = $(document).scrollTop();
        console.log(pos);
        if (pos > 180) {
            $(".top_panel").css("opacity", 1);
        } else {
            $(".top_panel").css("opacity", 0);
        }
    });

    $(".search_panel_input").keyup(search).keydown(function () {
        clearTimeout(timer);
    });
    $("#people").click(changeToPeople);
    $("#movie").click(changeToMovie);

});