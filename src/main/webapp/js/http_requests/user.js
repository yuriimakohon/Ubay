async function user_get() {
    let response = await fetch('http://localhost:8080/api/user/' + localStorage.getItem('id'), {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        return await response.json();
    } else {
        return null;
    }
}

async function addBalance() {
    await changeBalance($('#input-dollar').val());
}

async function changeBalance(dollars) {
    let user = {
        balance: dollars
    }

    let response = await fetch('http://localhost:8080/api/user?tab=change_balance', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    })
    if (response.ok) {
        let cb = $('#counter-balance');
        cb.text(+cb.text() + +dollars);
        localStorage.setItem('balance', cb.text());
    } else {
        console.log(await response.text());
    }
}


async function changePassword(password) {
    let cl = {
        password: sha512(password)
    };

    let response = await fetch('http://localhost:8080/api/user?tab=change_pass', {
        method: 'PUT',
        credentials: "same-origin",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(cl)
    });
    return response.ok;
}

async function changeLogin(login) {
    let cl = {
        login: login
    };

    let response = await fetch('http://localhost:8080/api/user?tab=change_login', {
        method: 'PUT',
        credentials: "same-origin",
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(cl)
    });
    return response.ok;
}

async function logout() {
    await fetch('http://localhost:8080/api/user?tab=log_out', {
        method: 'PUT',
        credentials: "same-origin"
    });
}


async function login() {

}

async function changePhoto(photo) {
    if (photo == null) {
        return;
    }
    let user = {
        photo: await loadFiles(photo)
    };

    let response = await fetch('http://localhost:8080/api/user?tab=change_photo', {
        method: 'PUT',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    });
    if (response.ok) {
        let json = await response.json();
        localStorage.setItem('avatar', json.avatar);
        return true
    } else {
        return false;
    }
}