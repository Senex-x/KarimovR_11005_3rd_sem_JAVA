<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/style.css">
    <title>File upload</title>
</head>
<body>

<#include "top.ftl">

<div class="wrapper">

<form action="/upload-image" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit">
</form>

</div>
</body>
</html>