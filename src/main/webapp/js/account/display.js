let changePasswordContainer = $('#change_pass-container');
let loginItem = $('#login-item');
let btnChangePass = $('#btn-change_pass');
let infoText = $('#info_text');

function onChangePassword() {
    loginItem.addClass('hidden');
    btnChangePass.addClass('hidden');
    changePasswordContainer.removeClass('hidden');
}

function onCancelPass() {
    loginItem.removeClass('hidden');
    btnChangePass.removeClass('hidden');
    changePasswordContainer.addClass('hidden');
}

function onSavePass() {
    infoText.text('Password was successfully changed');
    infoText.addClass('success_text');
    showInfoText();
    setTimeout(hideInfoText, 3000);
}

function hideInfoText() {
    infoText.addClass('hidden');
}

function showInfoText() {
    infoText.removeClass('hidden');
}