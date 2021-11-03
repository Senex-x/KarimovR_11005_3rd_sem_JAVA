$(document).ready(function () {
    $("#button-delete-cart")
        .click(function () {
            deleteCart()
        })
})

function deleteCart() {
    $.ajax({
        url: '/delete-cart',
        type: 'POST',
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        success: function () {
            console.log("Cart deleted successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}