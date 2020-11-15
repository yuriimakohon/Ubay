<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Account</title>

    <link rel="stylesheet" href="css/account/account.css">
    <%@include file="parts/resources.jsp" %>
    <script src="js/parts/sha512.js"></script>
    <script src="js/parts/parseCookie.js"></script>

    <script src="js/http_requests/user.js"></script>
    <script src="js/parts/ref_token.js"></script>
</head>
<body>
    <%@include file="parts/header_bar.jsp"%>

    <div class="main_container">
        <h2 id="title-my_account" class="title_text">My account</h2>
        <div class="title_line"></div>

        <div id="card-account_settings" class="card">
            <h3>Account settings</h3>
            <div id="info_text" class="hidden"></div>
            <div id="avatar-item" class="text_input-item">
                <p>Avatar</p>
                <input id="input-avatar" type="file" accept="image/*">
                <button id="btn-save_avatar" class="btn" onclick="onSaveAvatar()">save</button>
            </div>
            <div id="login-item" class="text_input-item">
                <p>Login</p>
                <input class="text_input" type="text" id="input-login" placeholder="Enter your login" maxlength="21">
                <button id="btn-save_login" class="btn" onclick="onSaveLogin()">apply</button>
            </div>
            <div class="text_input-item">
                <p>Password</p>
                <button id="btn-change_pass" class="btn" onclick="onChangePassword()">change password</button>
                <div id="change_pass-container" class="hidden">
                    <input class="text_input" type="password" id="input-new_pass" placeholder="Enter new password" maxlength="30">
                    <input class="text_input" type="password" id="input-confirm_pass" placeholder="Confirm new password" maxlength="30">
                    <div id="change_pass_btn-container">
                        <button id="btn-cancel_pass" class="btn btn-red" onclick="onCancelPass()">cancel</button>
                        <button id="btn-save_pass" class="btn" onclick="onSavePass()">apply</button>
                    </div>
                </div>
            </div>
            <button id="btn-logout" class="btn" onclick="onLogOut()">Log out</button>
        </div>
        <div class="card">
            <h3>Balance</h3>
            <label for="input-dollar"></label>
            <input id="input-dollar" class="number_input" type="number" step="5" min="0" placeholder="0.00">
            <button id="btn-add_money" class="btn" onclick="addBalance()">add</button>
        </div>
    </div>

    <script src="js/account/queries.js"></script>
    <script src="js/account/display.js"></script>
    <script src="js/account/confirm.js"></script>
    <script src="js/account/info_text.js"></script>
    <script src="js/parts/validation.js"></script>
    <%@include file="parts/scripts.jsp"%>
</body>
</html>
