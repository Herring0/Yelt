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
        $(".star").css("color", "var(--eggshell)");
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
        console.log(date + " " + time);

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
        $(".my_vote").text(ind);

        var xhr = new XMLHttpRequest();

        var body = 'rating=' + encodeURIComponent(ind) + "&" + "date=" + date + " " + time;
        console.log(body);

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        xhr.open("POST", window.location.href, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                callback(xhr.response);
            }
        };

        xhr.onloadend = function () {
            if (xhr.status == 404) ;
        }

        xhr.send(body);
    }

    $(".video img").click(show_video);
    $(".video_btn_container").click(show_video);
    $(".i_video_close").click(hide_video);
    $(".video_show_container").click(hide_video);
    $(".star").click(rate);

    $('.review').readmore({
        moreLink: '<a href="#">read more</a>',
        lessLink: '<a href="#">read less</a>',
        collapsedHeight: 500,
        speed: 500,
        afterToggle: function (trigger, element, expanded) {
            if (!expanded) {
                $('html, body').animate({scrollTop: $(element).offset().top}, {duration: 100});
            }
        }
    });
});

