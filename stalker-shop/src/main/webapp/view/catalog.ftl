<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>

<div id="top_img">

    <button class="sign-in" onclick="window.location.href='sign-in'">Войти</button>
    <button class="sign-up" onclick="window.location.href='sign-up'">Зарегистрироваться</button>
    <button class="shopping-cart" onclick="window.location.href='cart'">Корзина</button>

</div>

<div class="wrapper">

    <div class="container">
        <div class="item">
            <!--            <img src="../resources/images/content_title1_frame.png">-->
            <img src="../resources/images/seva.png" class="item">
            <button class="button-add-to-cart">➕</button>
            <img src="../resources/images/seva-info.jpg" height="200">
            <div class="costil"></div>
        </div>
        <div class="item">
            <img src="../resources/images/${cossacks-vodka.imageName}" class="item">
            <div>${cossacks-vodka.name}</div>
            <button class="button-add-to-cart">➕</button>
            <img src="" height="200">
            <div class="costil"></div>
        </div>

    </div>


</div>
</body>
</html>