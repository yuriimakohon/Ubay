<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Authorization</title>

    <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">

    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@100;300;400;600&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/authorization/auth.css">
</head>
<body>
    <div id="main_container">
        <header id="header-logo">
            <img id="logo" src="resources/ubay_logo.svg" alt="ubay logo">
        </header>
        <div class="auth_container">
            <div class="auth_navigation">
                <button id="tab-signup" class="auth_tab active" onclick="onTabSigup()">
                    Sign up
                </button>
                <button id="tab-signin" class="auth_tab" onclick="onTabSigin()">
                    Sign in
                </button>
            </div>
            <div class="auth_content">
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
            </div>
        </div>
    </div>

    <script src="js/authorization/auth.js" type="text/javascript"></script>
</body>
</html>
