<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/cart.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
    <script src="/resources/js/cart.js" type="text/javascript"></script>
    <script src="/resources/js/button_scroll_up.js" type="text/javascript"></script>
</head>
<body>
<#include "header.ftl">
<div class="wrapper">
    <div class="container">
        <div class="container-options">
            <div class="options-info">
                <p class="info-total text-bigger">Total: ${totalCost} ₽</p>
                <p class="info-count">
                    ${items?size} <#if items?size == 1>item<#else>items</#if>
                </p>
            </div>
            <div class="options-actions">
                <button class="actions-buy">
                    Buy
                    <i class="material-icons-outlined">attach_money</i></button>
                <button class="actions-clear">
                    Clear
                    <i class="material-icons-outlined">remove_shopping_cart</i></button>
            </div>
        </div>
        <#list items as item>
            <div class="item">
                <img class="item-image" src="../resources/images/items/${item.imageName}" alt="Item image">
                <div class="item-info">
                    <p class="info-name text-bigger">${item.name}</p>
                    <p class="info-price">${item.cost} ₽</p>
                </div>
                <button class="item-button-remove" id="${item?index}" >
                    Remove
                    <i class="material-icons-outlined">clear</i>
                </button>
            </div>
        </#list>
    </div>
</div>
<#include  "button_scroll_up.ftl">
</body>
</html>