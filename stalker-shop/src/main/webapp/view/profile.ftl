<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/profile.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
</head>
<body>
<header>
    <div class="header-wrapper">
        <img class="header-icon" src="/resources/images/design/logo_stalker.png" alt="Site logo"/>
        <p class="header-name">Stalker shop</p>
    </div>
</header>
<div class="wrapper">
    <section class="user-section">
        <#if user.avatarId != 0>
            <img class="user-section-avatar" src="/files/${user.avatarId}" alt="User image" onclick="window.location.href='upload-image'"/>
        <#else>
            <img class="user-section-avatar" src="/resources/images/profile/image_default.png" alt="Default image" onclick="window.location.href='upload-image'"/>
        </#if>
        <div class="user-section-info">
            <p class="info-name">${user.name}</p>
            <p class="info-email">${user.email}</p>
        </div>
        <div class="user-section-actions">
            <button class="actions-add-image" onclick="window.location.href='upload-image'">
                Add image
                <i class="material-icons-outlined">photo_camera</i>
            </button>
            <button class="actions-cart" onclick="window.location.href='cart'">
                Cart
                <i class="material-icons-outlined">shopping_cart</i>
            </button>
            <button class="actions-catalog" onclick="window.location.href='catalog'">
                Catalog
                <i class="material-icons-outlined">subject</i>
            </button>
            <button class="actions-sign-out" onclick="window.location.href='sign-out'">
                Sign out
                <i class="material-icons-outlined">logout</i>
            </button>
        </div>
    </section>
</div>
</body>
</html>