let changePasswordContainer = $('#change_pass-container');
let loginItem = $('#login-item');
let btnChangePass = $('#btn-change_pass');
let avatarItem = $('#avatar-item');
let btnLogout = $('#btn-logout');

$('#input-dollar').before('Add ').after(' $'); // TODO: add credit-card

function onChangePassword() {
    btnLogout.addClass('hidden');
    loginItem.addClass('hidden');
    avatarItem.addClass('hidden');
    btnChangePass.addClass('hidden');
    changePasswordContainer.removeClass('hidden');
}

function onCancelPass() {
    btnLogout.removeClass('hidden');
    loginItem.removeClass('hidden');
    avatarItem.removeClass('hidden');
    btnChangePass.removeClass('hidden');
    changePasswordContainer.addClass('hidden');
}
