<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Authorization</title>

    <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@100;300;400;600&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/node-forge@0.7.0/dist/forge.min.js"></script>
    <link rel="stylesheet" href="css/authorization/auth.css">
    <script src="js/parts/sha512.js"></script>
    <script src="js/parts/ref_token.js"></script>
</head>
<body>
    <div id="main_container">
        <header id="header-logo">
            <a href="http://localhost:8080/">
                <img id="logo" src="resources/ubay_logo.svg" alt="ubay logo">
            </a>
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
            <div id="error_text" class="hidden"></div>
            <div class="auth_content" id="auth_content-signup">
                <div class="auth_item">
                    <p>Login</p>
                    <input class="text_input" type="text" id="input-signup_login" placeholder="Enter your login" maxlength="21" oninput="hideErrorText()">
                </div>
                <div class="auth_item">
                    <p>Password</p>
                    <input class="text_input" type="password" id="input-signup_pass" placeholder="Come up with a password" maxlength="30" oninput="hideErrorText()">
                </div>
                <div class="auth_item">
                    <p>Confirm password</p>
                    <input class="text_input" type="password" id="input-signup_pass_confirm" placeholder="Repeat your password" maxlength="30" oninput="hideErrorText()">
                </div>
                <div class="auth_item">
                    <p>Role</p>
                    <label style="margin-right: 65px;">
                        <input id="rbtn-auctioner" type="radio" name="role" checked="checked" value="1">
                        Auctioneer
                    </label>
                    <label>
                        <input type="radio" name="role" value="2">
                        Bidder
                    </label>
                </div>
                <button class="btn" id="btn-create" onclick="onCreateAccount()">
                    Create account
                </button>
            </div>
            <div class="auth_content hidden" id="auth_content-signin">
                <div class="auth_item">
                    <p>Login</p>
                    <input class="text_input" type="text" id="input-signin_login" placeholder="Enter your login" maxlength="21" oninput="hideErrorText()">
                </div>
                <div class="auth_item">
                    <p>Password</p>
                    <input class="text_input" type="password" id="input-signin_pass" placeholder="Enter your password" maxlength="30" oninput="hideErrorText()">
                </div>
                <button class="btn" id="btn-login" onclick="onLogIn()">
                    Log in
                </button>
            </div>
        </div>
    </div>

    <script src="js/parts/validation.js" type="text/javascript"></script>
    <script src="js/authorization/display.js" type="text/javascript"></script>
    <script src="js/authorization/confirm.js" type="text/javascript"></script>
    <script src="js/authorization/errors.js" type="text/javascript"></script>
    <script src="js/authorization/request.js" type="text/javascript"></script>
</body>
</html>
