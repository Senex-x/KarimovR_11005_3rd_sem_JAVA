<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/cart.js" type="text/javascript"></script>
</head>
<body>
<button class="button-option" id="button-delete-cart">Очистить корзину</button>
<div id="cart-list">
    <#list items as item>
        <div style="font-size: 20px; margin-top: 10px">
            <!-- TODO: Do item handling here -->
            ${item.name}
        </div>
    </#list>
</div>
</body>
</html>
