<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="../../resources/css/style_profile_test.css">
    <style>
        .wrapper {
            margin: 20px 14%;
            padding: 20px;
        }

        .user-section {
            display: flex;
            flex-wrap: wrap;
            padding: 40px;
            background: #0e2436;
        }

        .user-section-info {
            justify-content: center;
            display: flex;
            flex-direction: column;
        }

        .user-section-info > p {
            margin: 0;
        }

        .user-section-name {
            font-size: 28px;
            padding-bottom: 20px;
            color: white;
        }

        .user-section-email {
            font-size: 20px;
            color: rgba(255, 255, 255, 0.71);
        }

        .user-section-avatar {
            margin-right: 40px;
            height: 300px;
            width: 300px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
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
            <p class="user-section-name">${user.name}</p>
            <p class="user-section-email">${user.email}</p>
        </div>
    </section>
</div>
</body>
</html>