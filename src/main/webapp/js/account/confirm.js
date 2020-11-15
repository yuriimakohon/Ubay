let inputNewPass = $('#input-new_pass');
let inputConfirmPass = $('#input-confirm_pass');
let inputLogin = $('#input-login');
let inputAvatar = $('#input-avatar');

async function onSavePass() {
    await ref_token();

    let pass = inputNewPass.val();
    if (!REGEX_PASS.test(pass)) {
        errPassFormat();
    } else if (pass.localeCompare(inputConfirmPass.val()) !== 0) {
        errPassConfirm();
    } else {
        if (await changePassword(inputNewPass.val())) {
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
            successNewLogin();
        } else {
            loginExists();
        }
    }
}

async function onSaveAvatar() {
    if (inputAvatar[0].files.length !== 1) {
        errAvatar();
    } else {
        if (await changePhoto(inputAvatar[0])) {
            $('#img-account').attr('src', localStorage.getItem('avatar'))
            successNewAvatar();
        } else {
            errAvatar();
        }
    }
}

async function onLogOut() {
    await logout();
    localStorage.clear();
    window.location.replace('/authorization');
}