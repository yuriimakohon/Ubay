let changePasswordContainer = $('#change_pass-container');
let loginItem = $('#login-item');
let btnChangePass = $('#btn-change_pass');

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
