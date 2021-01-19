// When the user scrolls the page, execute myFunction
window.onscroll = function() {myFunction()};

function myFunction() {
    var winScroll = document.body.scrollTop || document.documentElement.scrollTop;
    var height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
    var scrolled = (winScroll / height) * 100;
    document.getElementById("myBar").style.width = scrolled + "%";
}

(function(){
    var loader = document.getElementById("loading"),
        show = function(){
            loader.style.display = "flex";
            setTimeout(hide, 900); // 2 seconds
        },
        hide = function(){
            loader.style.display = "none";
        };
    show();
})();