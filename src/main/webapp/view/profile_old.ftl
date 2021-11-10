<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>

<#include "top.ftl">

<div class="wrapper">
    <div class="container">
        <button class="add-photo-button" onclick="window.location.href='upload-image'">Добавить фото</button>
        <button class="catalog-button" onclick="window.location.href='catalog'">Каталог</button>
        <#if user.avatarId != 0>
            <img class="user-avatar" alt="User image" src="/files/${user.avatarId}"/>
        <#else>
            <img class="user-avatar" alt="Default image" src="/resources/images/profile/image_default.png"/>
        </#if>
    </div>
</div>
</body>
</html>