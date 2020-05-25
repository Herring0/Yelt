$(document).ready(function(){
     $(".search_button").mouseover(function(){
         $(".search_input").css("border", "2px solid var(--terra_cotta)");
     });

     $(".search_button").mouseleave(function() {
     	$(".search_input").css("border", "2px solid #1D222850");
     })
});