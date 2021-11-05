<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../../resources/css/style_catalog_test.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog.js" type="text/javascript"></script>
    <style>
        .item-button-add {
            vertical-align: center;
            cursor: pointer;
            border-width: 2px;
            border-style: solid;
            border-color: #dc9d31;
            color: white;
            background-color: #0e2436;
            font-size: 20px;
            padding: 10px;
            margin-bottom: 20px;
        }

        .item-button-add:hover {
            background-color: #a4781b;
        }

        .item-button-add {
            margin: 20px 0 0 0;
            flex-grow: 0;
            align-self: end;
        }

        .item {
            display: flex;
            flex-direction: column;
            margin: 20px;
            padding: 20px;
            /*
            * These three can be simplified
            * and turned into 'flex: 1'
            */
            flex-basis: 0;
            flex-grow: 1;
            flex-shrink: 1;
            min-width: 400px;
            max-width: 600px;
            outline: solid white 1px;
        }

        .item-header {
            flex-grow: 0;
            display: flex;
        }

        .item p {
            font-family: sans-serif;
            color: white;
        }

        .item-description {
            margin: 20px 0 0 0;
            flex-grow: 1;
        }
    </style>
</head>
<body>
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
            <button class="item-button-add">
                Add to cart
                <i class="material-icons-outlined">add_shopping_cart</i>
            </button>
        </div>
    </#list>
</section>
</body>
</html>