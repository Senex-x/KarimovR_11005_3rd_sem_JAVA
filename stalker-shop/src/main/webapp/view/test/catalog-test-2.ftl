<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../../resources/css/style_catalog_test.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog-test.js" type="text/javascript"></script>
    <style>
        header {
            display: flex;
            align-items: center;
            justify-content: end;
            width: 100%;
            height: 100px;
            z-index: 10;
            position: fixed;
            margin-top: 0;
            padding-top: 0;
            top: 0;
            background-color: #08151e;
        }

        .header-button-cart {
            cursor: pointer;
            border-width: 2px;
            border-style: solid;
            border-color: #dc9d31;
            /*border-color: #cb6b33;*/
            color: white;
            background-color: #0e2436;
            font-size: 20px;
            padding: 10px;
        }

        .header-wrapper {
            display: flex;
            justify-content: end;
            margin: 20px 15% 20px 15%;
        }

        .header-wrapper > * {
            margin-left: 20px;
        }
    </style>
</head>
<body>
<header>
    <div class="header-wrapper">
        <button class="header-button-cart" onclick="window.location.href='cart'">
            Go to cart
            <i class="material-icons-outlined">shopping_cart</i>
        </button>
        <button class="header-button-cart" onclick="window.location.href='profile'">
            Profile
            <i class="material-icons-outlined">account_circle</i>
        </button>
    </div>
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