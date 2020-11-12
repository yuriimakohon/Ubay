let inputNewPass = $('#input-new_pass');
let inputConfirmPass = $('#input-confirm_pass');
let inputLogin = $('#input-login');

async function onSavePass() {
    let pass = inputNewPass.val();
    if (!REGEX_PASS.test(pass)) {
        errPassFormat();
    } else if (pass.localeCompare(inputConfirmPass.val()) !== 0) {
        errPassConfirm();
    } else {
        let cp = {
            password: sha512(inputNewPass.val())
        };

        let response = await fetch('http://localhost:8080/account/change_pass', {
            method: 'PUT',
            credentials: "same-origin",
            body: JSON.stringify(cp)
        });
        if (response.ok) {
            console.log("resp ok");
            successNewPass();
        } else {
            console.log("error");
        }
    }
}

async function onSaveLogin() {
    if (!REGEX_LOGIN.test(inputLogin.val())) {
        errLoginFormat();
    } else {
        let cl = {
            login: inputLogin.val()
        };

        let response = await fetch('http://localhost:8080/account/change_login', {
            method: 'PUT',
            credentials: "same-origin",
            body: JSON.stringify(cl)
        });
        if (response.ok) {
            console.log("resp ok");
            successNewLogin();
        } else {
            console.log("error");
        }
    }
}

async function onLogOut() {
    await fetch('http://localhost:8080/user/log_out', {
        method: 'PUT',
        credentials: "same-origin"
    });
    localStorage.clear();
    window.location.replace('/authorization');
}