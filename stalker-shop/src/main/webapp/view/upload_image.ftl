<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/sign_form.css">
    <link rel="stylesheet" href="../resources/css/upload_form.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/upload_image.js" type="text/javascript"></script>

    <title>File upload</title>
</head>
<body>
<#include "header.ftl">
<div class="wrapper">
    <section class="section-form">
        <form class="form-upload-image" action="/upload-image" method="post" enctype="multipart/form-data">
            <p class="text-header">Pick image</p>
            <label for="form-file-upload" class="form-button-file-upload">
                Upload image
            </label>
            <p id="form-status" class="text-bigger">Image not chosen</p>
            <input id="form-file-upload" type="file" name="file" accept=".jpg, .jpeg, .png">
            <input id="form-submit" type="submit" value="Update">
            <button class="button-delete-profile-image">Delete profile image</button>
        </form>
    </section>

</div>
</body>
</html>