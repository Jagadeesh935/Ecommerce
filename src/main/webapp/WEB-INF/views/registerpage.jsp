<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registeration</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <form action="/register" method="post" enctype="multipart/form-data">
        <div class="title">User Registeration</div>
        <div class="field">
            <label for="username">Username</label>
            <input type="text" name="username">
        </div>
        <div class="field">
            <label for="password">Password</label>
            <input type="password" name="password">
        </div>
        <div class="field">
            <label for="cpassword">Confirm Password</label>
            <input type="password" name="cpassword">
        </div>
        <div class="field">
            <label for="mobile">Mobile Number</label>
            <input type="number" name="mobile">
        </div>
        <div class="field">
            <label for="door">Door Number</label>
            <input type="text" name="door">
        </div>
        <div class="field">
            <label for="street">Street</label>
            <input type="text" name="street">
        </div>
        <div class="field">
            <label for="city">City/village</label>
            <input type="text" name="city">
        </div>
        <div class="field">
            <label for="country">Country</label>
            <select name="country">
                <option value=""></option>
                <option value="India">India</option>
            </select>
        </div>
        <div class="field">
            <label for="state">State</label>
            <select name="state">
                <option value=""></option>
                <option value="Tamil Nadu">Tamil Nadu</option>
                <option value="Kerala">Kerala</option>
            </select>
        </div>
        <div class="field">
            <label for="district">District</label>
            <select name="district">
                <option value=""></option>
                <option value="Coimbatore">Coimbatore</option>
                <option value="Tiruppur">Tiruppur</option>
                <option value="Madurai">Madurai</option>
                <option value="Chennai">Chennai</option>
            </select>
        </div>
        <div class="field">
            <label for="pic">Profile picture</label>
            <input type="file" name="profilepic">
        </div>
        <div class="buttons">
            <button>Submit</button>
            <button>Clear</button>
        </div>
		<div style="text-align: center; margin-bottom: 20px;" class="div">Already have an account? <a href="/loginpage">Sign in</a></div>
    </form>
</body>
</html>