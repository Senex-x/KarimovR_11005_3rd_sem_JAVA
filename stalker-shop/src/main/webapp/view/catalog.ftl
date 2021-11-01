<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../resources/css/style.css">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/catalog.js"></script>
</head>
<body>

<#include "top.ftl">

<button id="button-add-to-cart">Add item to cart</button>

<div class="wrapper">

    <div class="container">
        <div class="item">
            <!--            <img src="../resources/images/content_title1_frame.png">-->
<<<<<<< HEAD
            <img src="../resources/images/items/seva.png" class="item">
            <button class="button-add-to-cart">➕</button>
            <img src="../resources/images/items/seva-info.jpg" height="200">
            <div class="width-item"></div>
=======
            <img src="../resources/images/items/seva.png" class="item">
            <button id="add_to_cart" class="button-add-to-cart">➕</button>
            <img src="../resources/images/items/seva-info.jpg" height="200">
            <div class="costil"></div>
            <div id="somediv">Somediv</div>
>>>>>>> 7053dabc2203f5c047c130256f5018db8bd556e9
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