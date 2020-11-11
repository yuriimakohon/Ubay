async function sign_up(login, password, role) {
    let params = {
        login: login,
        password: sha512(password),
        role: role
    };
    let url = 'http://localhost:8080/user/sign_up';

    let response = await fetch( url, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(params)
    });
    return machiningResponse(response);
}

async function sign_in(login, password) {
    let params = {
        login: login,
        password: sha512(password),
    };
    let url = 'http://localhost:8080/user/sign_in';

    let response = await fetch( url, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(params)
    });

    return machiningResponse(response);
}

async function machiningResponse(response) {
    if (response.status === 200) {
        let text = JSON.parse(await response.text());
        document.cookie = "token="+text.token + ';'
        document.cookie = "id="+text.id
        return true;
    } else {
        console.log("error " + response.status)
        return false;
    }
}
