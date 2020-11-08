let changePasswordContainer = $('#change_pass-container');
let loginItem = $('#login-item');
let btnChangePass = $('#btn-change_pass');
$('#input-dollar').before('Add '); // TODO: delete
$('#input-dollar').after(' $'); // TODO: delete

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
