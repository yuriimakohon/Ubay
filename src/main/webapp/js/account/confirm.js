let inputNewPass = $('#input-new_pass');
let inputConfirmPass = $('#input-confirm_pass');
let inputLogin = $('#input-login');

async function onSavePass() {
    await ref_token();

    let pass = inputNewPass.val();
    if (!REGEX_PASS.test(pass)) {
        errPassFormat();
    } else if (pass.localeCompare(inputConfirmPass.val()) !== 0) {
        errPassConfirm();
    } else {
        if (await changePassword(inputNewPass.val())) {
            console.log("resp ok");
            successNewPass();
        } else {
            console.log("error");
        }
    }
}

async function onSaveLogin() {
    await ref_token();

    if (!REGEX_LOGIN.test(inputLogin.val())) {
        errLoginFormat();
    } else {
        if (await changeLogin(inputLogin.val())) {
            console.log("resp ok");
            successNewLogin();
        } else {
            console.log("error");
        }
    }
}

async function onLogOut() {
    await logout();
    localStorage.clear();
    window.location.replace('/authorization');
}