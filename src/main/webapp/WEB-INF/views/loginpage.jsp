<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="error">${error}</div>
	<form action="/login" method="post">
    <div class="box">
        <div class="left">
            <h1 class="signin">Signin</h1>
            <div class="usernamefield field">
                <label for="username">Username</label>
                <input type="text" name="username">
            </div>
            <div class="passwordfield field">
                <label for="password">Password</label>
                <input type="password" name="password">
            </div>
            <input class="loginbutton" type="submit" value="Login">
            <a href="#" class="forgot">Forgot Password</a>
        </div>
        <div class="right">
            <h1 class="welcomeback">Welcome Back</h1>
            <p>Don't have an account</p>
            <button formaction="/registerpage" formmethod="get" class="signup">Sign up</button>
        </div>
    </div>
	</form>
</body>
</html>