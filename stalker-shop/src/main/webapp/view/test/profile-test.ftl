<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="../../resources/css/style_profile_test.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
</head>
<body>

<div class="wrapper">
    <section class="user-section">
        <#if user.avatarId != 0>
            <img class="user-section-avatar" alt="User image" src="/files/${user.avatarId}"/>
        <#else>
            <img class="user-section-avatar" alt="Default image" src="/resources/images/profile/image_default.png"/>
        </#if>
        <div class="user-section-info">
            <p class="user-section-info-name">${user.name}</p>
            <p class="user-section-info-email">${user.email}</p>
        </div>
        <div class="user-section-actions">
            <button class="user-section-actions-add-image" onclick="window.location.href='upload-image'">
                Add image
                <i class="material-icons-outlined">photo_camera</i>
            </button>
            <button class="user-section-actions-catalog" onclick="window.location.href='catalog'">
                Catalogue
                <i class="material-icons-outlined">subject</i>
            </button>
            <button class="item-button header-button-cart" onclick="window.location.href='cart'">
                Go to cart
                <i class="material-icons-outlined">shopping_cart</i>
            </button>
        </div>
    </section>
</div>
</body>
</html>