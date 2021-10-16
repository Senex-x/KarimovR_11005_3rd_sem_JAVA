<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <style>
        input {
            font-size: 25px;
            display: block;
            border: 2px solid #999999;
            border-radius: 15px;
            margin: 20px;
            padding: 20px;
            text-align: center;
        }
        body {
            color: bisque;
            background-color: bisque;
        }
    </style>
</head>
<body>
<form action="/sign-in" method="post">
    <label>
        <input name="username" type="text" placeholder="NAME"/>
    </label>
    <label>
        <input name="password" type="password" placeholder="PASSWORD"/>
    </label>
    <input type="submit" value="Sign in">
</form>
</body>
</html>