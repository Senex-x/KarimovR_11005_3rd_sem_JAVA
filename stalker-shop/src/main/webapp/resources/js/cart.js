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
    const itemNameHtml = document.getElementsByClassName("info-name").item(itemIndex)
    const itemName = itemNameHtml.textContent
    $(itemNameHtml.parentElement.parentElement).slideUp()

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
