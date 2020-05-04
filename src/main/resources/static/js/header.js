$(document).ready(function(){
     $(".search_button").mouseover(function(){
         $(".search_input").css("border", "2px solid #546a76");
     });

     $(".search_button").mouseleave(function() {
     	$(".search_input").css("border", "2px solid #f7c548");
     })
});