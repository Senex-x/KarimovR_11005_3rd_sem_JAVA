<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/sign_in.css">
</head>
<body>
<#include "header_base.ftl">
<div class="wrapper">
    <section class="section-register">
        <div class="register-container">
            <p class="register-header text-header">Sign in</p>
            <form class="register-form" method="post">
                <label>
                    <input class="form-email" placeholder="Email" name="email" type="email">
                </label>
                <label>
                    <input class="form-password" placeholder="Password" name="password" type="password">
                </label>
                <input class="button-form-submit" type="submit">
            </form>
        </div>
    </section>
</div>
</body>
</html>