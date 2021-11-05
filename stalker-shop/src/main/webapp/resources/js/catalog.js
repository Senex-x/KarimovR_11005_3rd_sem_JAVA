const itemName = "Cossacks vodka"

// TODO: Call click method with
//  corresponding itemName argument for each button
// If work, delete this TODO
$(document).ready(function () {
    $(".button-add-to-cart")
        .click(function (event) {
            const itemName = event.target.id
            addToCart(itemName)
        })
})

function addToCart(itemName) {
    $.ajax({
        url: '/add-to-cart',
        type: 'POST',
        data: "itemName=" + itemName,
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        success: function () {
            console.log("Item '" + itemName + "' added to cart successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}