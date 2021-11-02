const itemName = "Cossacks vodka"

function addToCart(itemName) {
    //let itemName = "Cossacks vodka"
    console.log("Button click")
    console.log(typeof itemName)
    console.log("itemName=" + itemName.valueOf())

    $.ajax({
        url: '/add-to-cart',
        type: 'POST',
        data: "itemName=".concat(itemName.valueOf()),
        headers: {
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        success: function () {
            alert("Item '" + itemName + "' added to cart successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}

$(document).ready(function () {
    $("#button-add-to-cart")
        .click(function () {
            addToCart("Cossacks vodka")
        })
})