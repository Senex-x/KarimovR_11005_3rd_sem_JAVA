<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
<div id="cart-list">
    <#list items as item>
        <div>
            <!-- TODO: Do item handling here -->
            ${item.name}
        </div>
    </#list>
</div>
</body>
</html>
