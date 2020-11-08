async function sign_up(login, password, role) {
    let urlParams = new URLSearchParams();

    urlParams.append('login', login);
    urlParams.append('password', sha512(password));
    urlParams.append('role', role);

    let response = await fetch( 'http://localhost:8080/ubay/sign_up?' + urlParams.toString(), {
        method: 'POST',
    })
    if (response.ok) {
        let text = await response.text();
        console.log("resp: " +  text)
    } else {
        console.log("error")
    }
    return response.ok;
}

async function sign_in(login, password) {
    let urlParams = new URLSearchParams();

    urlParams.append('login', login);
    urlParams.append('password', sha512(password));

    let response = await fetch( 'http://localhost:8080/ubay/sign_in?' + urlParams.toString(), {
        method: 'POST',
    })
    if (response.ok) {
        let text = await response.text();
        console.log("resp: " +  text)
    } else {
        console.log("error")
    }
    return response.ok;
}

function sha512(str) {
    let md = forge.md.sha512.create().update(str);
    return md.digest().toHex()
}