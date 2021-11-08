<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="icon" type="image/x-icon" href="/resources/images/design/icon_stalker.ico"/>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/item_info.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/item_info.js" type="text/javascript"></script>
</head>
<body>
<#include "header.ftl">
<section class="wrapper">
    <div class="item" data-item-name="{item.name}">
        <div class="item-header">
            <img class="item-image" alt="Item image">
            <div class="item-info">
                <p class="info-name text-bigger-bold"></p>
                <p class="info-price"></p>
            </div>
        </div>
        <p class="item-description"></p>
        <button class="item-button-add" onclick="addItemToCart()">
            Add to cart
            <i class="material-icons-outlined">add_shopping_cart</i>
        </button>
    </div>
</section>
</body>
</html>