$(document).ready(function () {
    let scrollButton = document.getElementById("button-scroll-to-top")
    window.onscroll = function () {
        if (document.body.scrollTop > 400 || document.documentElement.scrollTop > 400) {
            scrollButton.style.display = "block";
        } else {
            scrollButton.style.display = "none";
        }
    };
})

function onScrollButtonClick() {
    // For Safari
    document.body.scrollTop = 0;
    // For Chrome, Firefox, IE and Opera
    document.documentElement.scrollTop = 0;
}
