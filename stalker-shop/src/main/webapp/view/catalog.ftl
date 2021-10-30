<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>

<#include "top.ftl">

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
            <img src="../resources/images/${cossacks-vodka.imageName}" class="item">
            <div>${cossacks-vodka.name}</div>
            <button class="button-add-to-cart">➕</button>
            <img src="" height="200">
            <div class="width-item"></div>
        </div>

    </div>


</div>
</body>
</html>