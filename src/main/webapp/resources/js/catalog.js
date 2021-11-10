$(document).ready(function () {
    $(".item").click(function () {
        openItemInfo(
            $(this).data("item-name")
        )
    })

    $(".item-button-add").click(function (event) {
        // To stop triggering parent's click listener
        event.stopPropagation();
    })
})

function openItemInfo(itemName) {
        window.location = '/item-info?itemName=' + itemName;
}

function addItemToCart(itemIndex) {
    const itemName = $(".info-name").eq(itemIndex).text()

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
