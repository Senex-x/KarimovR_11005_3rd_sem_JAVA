$(document).ready(function () {
    $(".item-button-add").click(function (event) {
        const itemName = event.currentTarget.id
        console.log("Click button with id  '" + itemName + "'")
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