<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../resources/css/style.css">
    <title>File upload</title>
</head>
<body>
<div id="top_img">

    <button class="sign-in" onclick="window.location.href='sign-in'">Войти</button>
    <button class="sign-up" onclick="window.location.href='sign-up'">Зарегистрироваться</button>
    <button class="shopping-cart">Корзина</button>

</div>

<div class="wrapper">

<form action="/upload-image" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit">
</form>

</div>
</body>
</html>