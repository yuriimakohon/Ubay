<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Signup</title>

    <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">

    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@100;300;400;600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/signup/signup.css">
</head>
<body>
    <div id="main_container">
        <header id="header-logo">
            <img id="logo" src="resources/ubay_logo.svg" alt="ubay logo">
        </header>
        <p id="p-registration">Registration</p>
        <div class="auth_item">
            <p>Login</p>
            <input class="text_input" type="text" placeholder="Enter your login" maxlength="21">
        </div>
        <div class="auth_item">
            <p>Password</p>
            <input class="text_input" type="password" placeholder="Come up with a password" maxlength="30">
        </div>
        <div class="auth_item">
            <p>Confirm password</p>
            <input class="text_input" type="password" placeholder="Repeat your password" maxlength="30">
        </div>
        <div class="auth_item">
            <p>Role</p>
            <label style="margin-right: 65px;">
                <input type="radio" name="role" value="auctioneer">
                Auctioneer
            </label>
            <label>
                <input type="radio" name="role" value="bidder">
                Bidder
            </label>
        </div>
        <div class="btn" id="btn-create">
            <a>Create account</a>
        </div>
        <a href="#" id="a-login">Already have an account?</a>
    </div>
</body>
</html>
