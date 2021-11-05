<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../../resources/css/style_catalog_test.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog-test.js" type="text/javascript"></script>
    <style>
        header {
            display: flex;
            flex-direction: row-reverse;
            align-items: end;
            width: 100%;
            height: 100px;
            z-index: 10;
            position: fixed;
            top: 0;
            background-color: #08151e;
        }

        .header-button-cart {
            margin: 20px 15%  20px 0;
        }
    </style>
</head>
<body>
<header>
 <button class="item-button header-button-cart" onclick="window.location.href='cart'">
     Go to cart
     <i class="material-icons-outlined">shopping_cart</i>
 </button>
</header>
<section class="wrapper">
    <#list items as item>
        <div class="item">
            <div class="item-header">
                <img class="item-image" src="../resources/images/items/${item.imageName}" alt="Item image">
                <div class="item-header-container">
                    <p class="item-name">${item.name}</p>
                    <p class="item-price">${item.cost} ₽</p>
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