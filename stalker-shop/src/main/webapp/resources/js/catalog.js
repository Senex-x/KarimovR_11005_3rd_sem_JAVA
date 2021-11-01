$(document).ready(function () {
    $("#button-add-to-cart").click(function () {
        console.log("Button clicked")

        $.ajax({
            url: '/add-to-cart',
            type: 'POST',
            data: "content=vodka",
            headers: {
                 contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            success: function (data) {
                //code to open in new window comes here
                console.log(data)
            }
        });
    })
})