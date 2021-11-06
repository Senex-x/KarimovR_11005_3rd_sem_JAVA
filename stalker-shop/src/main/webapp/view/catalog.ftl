<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="/resources/css/catalog.css">
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog-test.js" type="text/javascript"></script>
</head>
<body>
<header>
    <div class="header-wrapper">
        <img class="header-icon" src="/resources/images/design/logo_stalker.png" alt="Site logo"/>
        <p class="header-name">Stalker shop</p>
        <button class="header-button-cart" onclick="window.location.href='cart'">
            Cart
            <i class="material-icons-outlined">shopping_cart</i>
        </button>
        <button class="header-button-profile" onclick="window.location.href='profile'">
            Profile
            <i class="material-icons-outlined">account_circle</i>
        </button>
    </div>
</header>
<section class="wrapper">
    <#list items as item>
        <div class="item">
            <div class="item-header">
                <img class="item-header-image" src="../resources/images/items/${item.imageName}" alt="Item image">
                <div class="item-header-info">
                    <p class="item-header-info-name">${item.name}</p>
                    <p class="item-header-info-price">${item.cost} ₽</p>
                </div>
            </div>
            <p class="item-description">${item.description}</p>
            <button class="item-button-add" id="${item.name}">
                Add to cart
                <i class="material-icons-outlined">add_shopping_cart</i>
            </button>
        </div>
    </#list>
</section>
</body>
</html>