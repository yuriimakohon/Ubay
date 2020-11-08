async function sign_up(login, password, role) {
    let urlParams = new URLSearchParams();

    urlParams.append('login', login);
    urlParams.append('password', sha512(password));
    urlParams.append('role', role);

    let response = await fetch( 'http://localhost:8080/ubay/sign_up?' + urlParams.toString(), {
        method: 'POST',
    })
    return machiningResponse(response);
}

async function sign_in(login, password) {
    let urlParams = new URLSearchParams();

    urlParams.append('login', login);
    urlParams.append('password', sha512(password));

    let response = await fetch( 'http://localhost:8080/ubay/sign_in?' + urlParams.toString(), {
        method: 'POST',
    })
    return machiningResponse(response);
}

async function machiningResponse(response) {
    if (response.status === 200) {
        let text = JSON.parse(await response.text());
        document.cookie = "token="+text.token
        return true;
    } else {
        console.log("error " + response.status)
        return false;
    }
}


function sha512(str) {
    let md = forge.md.sha512.create().update(str);
    return md.digest().toHex()
}