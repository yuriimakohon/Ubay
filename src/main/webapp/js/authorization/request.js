async function sign_up(login, password, role) {
    let user = {
        login: login,
        password: password,
        role: role
    }

    let response = await fetch( 'http://localhost:8080/ubay/auth', {
        method: 'POST',
        headers: {
            'request': 'sign_up',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    if (response.ok) {
        let resp = await response.text();
        console.log('ok: ' + resp);
    } else {
        console.log('error signup');
    }
}

async function sign_in(login, password) {
    let user = {
        login: login,
        password: password,
    }
    let response = await fetch( 'http://localhost:8080/ubay/auth', {
        method: 'POST',
        headers: {
            'request': 'sign_in',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    if (response.ok) {
        let resp = await response.text();
        console.log('ok: ' + resp);
    } else {
        console.log('error signup');
    }
}
