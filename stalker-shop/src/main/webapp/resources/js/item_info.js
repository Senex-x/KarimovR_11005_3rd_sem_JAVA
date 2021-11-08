$(document).ready(function () {
    const itemName = new URL(window.location.href).searchParams.get("itemName");

    getItemFromDB(itemName)
})

function getItemFromDB(itemName) {
    console.log("To get from db: " + itemName)

    $.ajax({
        url: '/get-item',
        data: "itemName=" + itemName,
        dataType: "json",
        success: function (data) {
            console.log("Json data received successfully")
            displayItemInfo(data)
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}

function displayItemInfo(item) {
    $(".item-image").attr("src", `../resources/images/items/${item.imageName}`)
    $(".info-name").text(item.name)
    $(".info-price").text(`Cost: ${item.cost} \u20bd`) //  ₽ == \u20bd
    $(".item-description").text(item.description)
}

function addItemToCart() {
    const itemName = $(".info-name").text()

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