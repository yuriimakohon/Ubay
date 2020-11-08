let inputNewPass = $('#input-new_pass');
let inputConfirmPass = $('#input-confirm_pass');
let inputLogin = $('#input-login');

function onSavePass() {
    let pass = inputNewPass.val();
    if (!REGEX_PASS.test(pass)) {
        errPassFormat();
    } else if (pass.localeCompare(inputConfirmPass.val()) !== 0) {
        errPassConfirm();
    } else {
        successNewPass();
        console.log(pass);
    }
}

function onSaveLogin() {
    if (!REGEX_LOGIN.test(inputLogin.val())) {
        errLoginFormat();
    } else {
        successNewLogin();
        console.log(inputLogin.val());
    }
}