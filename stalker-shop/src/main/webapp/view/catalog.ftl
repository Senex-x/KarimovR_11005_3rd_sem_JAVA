<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../resources/css/style.css">

    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/catalog.js" type="text/javascript"></script>
</head>
<body>

<#include "top.ftl">

<button id="button-add-to-cart">Add item to cart</button>

<div class="wrapper">
    <div class="container">
        <div class="item">
            <!--            <img src="../resources/images/content_title1_frame.png">-->
            <img src="../resources/images/items/seva.png" class="item">
            <button class="button-add-to-cart">➕</button>
            <img src="../resources/images/items/seva-info.jpg" height="200">
            <div class="width-item"></div>
        </div>

        <div class="item">
            <img src="../resources/images/${cossacksvodka.imageName}" class="item">
            <div>${cossacksvodka.name}</div>
            <button class="button-add-to-cart">➕</button>
            <img src="" height="200">
            <div class="width-item"></div>
        </div>
    </div>
</div>
</body>
</html>