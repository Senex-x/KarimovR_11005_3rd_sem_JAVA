function addItemToCart(itemName) {
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
