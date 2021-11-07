<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/cart.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/cart.js" type="text/javascript"></script>
</head>
<body>
<#include "header.ftl">

<!--
<button class="button-option" id="button-delete-cart">Очистить
    <i class="material-icons-outlined">remove_shopping_cart</i>
</button>
-->

<div class="wrapper">
    <div class="container">
        <div class="container-actions">
            <div class="actions-info">
                <p class="actions-total text-bigger">Total: 2150 ₽</p>
                <p class="actions-count">20 items</p>
            </div>
            <div class="actions-buttons">
                <button>
                    Buy
                    <i class="material-icons-outlined">attach_money</i></button>
                <button>
                    Clear
                    <i class="material-icons-outlined">remove_shopping_cart</i></button>
            </div>
        </div>
        <#list items as item>
            <div class="item">
                <img class="item-header-image" src="../resources/images/items/${item.imageName}" alt="Item image">
                <div class="item-header-info">
                    <p class="item-header-info-name text-bigger">${item.name}</p>
                    <p class="item-header-info-price">${item.cost} ₽</p>
                </div>
                <button class="item-button-remove" id="${item.name}">
                    Remove
                    <i class="material-icons-outlined">clear</i>
                </button>
            </div>
        </#list>
    </div>
</div>
</body>
</html>