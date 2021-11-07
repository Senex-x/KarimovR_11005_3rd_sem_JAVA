$(document).ready(function () {
    $(".actions-clear").click(function () {
        deleteCart()
    })

    $(".actions-buy").click(function () {
        deleteCart()
    })

    $(".item-button-remove").click(function (event) {
        deleteItemFromCart(event.currentTarget.id)
    })

    let scrollButton = document.getElementById("button-scroll-to-top")
    window.onscroll = function () {
        if (document.body.scrollTop > 400 || document.documentElement.scrollTop > 400) {
            scrollButton.style.display = "block";
        } else {
            scrollButton.style.display = "none";
        }
    };
})

function deleteItemFromCart(itemIndex) {
    // Update database
    const itemNameHtml = document
        .getElementsByClassName("info-name")
        .item(itemIndex)
    const itemName = itemNameHtml.textContent
    $(itemNameHtml.parentElement.parentElement).slideUp()
    removeCartItem(itemName)

    // Update total cost of the cart
    const totalCostHtml = document
        .getElementsByClassName("info-total")
        .item(0)
    const totalCost = getCostFromInfo(totalCostHtml.textContent)
    const itemCost = getCostFromItem(itemNameHtml.nextElementSibling.textContent)
    totalCostHtml.innerHTML = "Total: " + (totalCost - itemCost) + " &#8381"

    // Update item count of the cart
    const itemCounterHtml = document
        .getElementsByClassName("info-count")
        .item(0)
    const itemCount = getCountFromInfo(itemCounterHtml.textContent)
    console.log(itemCount)
    let pluralSuffix = ""
    if (itemCount !== 2) pluralSuffix = "s"
    itemCounterHtml.innerHTML = (itemCount - 1) + " item" + pluralSuffix
}

function getCostFromItem(costString) {
    return parseInt(costString.substring(
        0,
        costString.length - 2
    ), 10)
}

function getCostFromInfo(costString) {
    let cost = costString.substring(
        costString.indexOf(':') + 2,
        costString.length - 2
    )
    return parseInt(
        cost.replaceAll("\u00a0", ''), // nbsp character
        10
    )
}

function getCountFromInfo(countString) {
    return parseInt(countString
        .replaceAll(' ', '')
        .substring(
            0,
            countString.indexOf('i')
        ))
}

function removeCartItem(itemName) {
    $.ajax({
        url: "/remove-cart-item",
        type: "POST",
        data: "itemName=" + itemName,
        headers: {
            contentType: "application/x-www-form-urlencoded; charset=UTF-8"
        },
        success: function () {
            console.log("Item '" + itemName + "' deleted successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}

function deleteCart() {
    const items = document.getElementsByClassName("item")
    for (let i = 0, item; item = items[i]; i++) {
        $(item).slideUp()
    }

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

function onScrollButtonClick() {
    // For Safari
    document.body.scrollTop = 0;
    // For Chrome, Firefox, IE and Opera
    document.documentElement.scrollTop = 0;
}
