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
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(params)
    });
    if (response.ok) {
        let user = await response.json();
        localStorage.setItem("balance", user.balance);
        localStorage.setItem("role", user.role);
        localStorage.setItem("tokenTime", user.tokenTime);
        localStorage.setItem("time", user.time);
        localStorage.setItem("id", user.id);
        return true;
    } else {
        console.log(await response.text());
        return false;
    }
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
            'content-type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(params)
    });

    if (response.ok) {
        let user = await response.json();
        localStorage.setItem("balance", user.balance);
        localStorage.setItem("role", user.role);
        localStorage.setItem("tokenTime", user.tokenTime);
        localStorage.setItem("time", user.time)
        localStorage.setItem("id", user.id);
        return true;
    } else {
        console.log(await response.text());
        return false;
    }
}
