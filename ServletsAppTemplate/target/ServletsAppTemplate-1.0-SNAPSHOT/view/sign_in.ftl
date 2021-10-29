<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<script>
    let element = document.querySelector(".main")
    element.addEventListener('click', (event) => {
        element.removeAttribute('hidden')
    })
</script>
<body>
<h1>Sign in</h1>

<form method="post">
    <label>Email
        <input name="email" type="email">
    </label>
    <label>Password
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>

</body>
</html>