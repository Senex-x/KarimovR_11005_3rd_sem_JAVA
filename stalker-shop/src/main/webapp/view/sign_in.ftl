<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TEMP-SIGN-UP</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>

<div id="top_img">

    <button class="sign-in" onclick="window.location.href='sign-in'">Войти</button>
    <button class="sign-up" onclick="window.location.href='sign-up'">Зарегистрироваться</button>
    <button class="shopping-cart" onclick="window.location.href='cart'">Корзина</button>
</div>

<div class="wrapper">

    <form method="post" id="forms">
        <label id="form">Email
            <input name="email" type="email">
        </label>
        <label id="form">Password
            <input name="password" type="password">
        </label>
        <input type="submit" id="form">
    </form>

</div>
</body>
</html>