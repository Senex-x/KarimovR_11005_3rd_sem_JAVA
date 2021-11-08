<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="/resources/css/catalog.css">
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog.js" type="text/javascript"></script>
    <script src="/resources/js/button_scroll_up.js" type="text/javascript"></script>
</head>
<body>
<#include "header.ftl">
<section class="wrapper">
    <#list items as item>
        <div class="item">
            <div class="item-header">
                <img class="item-image" src="../resources/images/items/${item.imageName}" alt="Item image">
                <div class="item-info">
                    <p class="info-name text-bigger-bold">${item.name}</p>
                    <p class="info-price">${item.cost} ₽</p>
                </div>
            </div>
            <p class="item-description">${item.description}</p>
            <button class="item-button-add" id="${item.name}">
                Add
                <i class="material-icons-outlined">add_shopping_cart</i>
            </button>
        </div>
    </#list>
</section>
<#include  "button_scroll_up.ftl">
</body>
</html>