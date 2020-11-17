async function ref_token() {
    let time = +new Date().getTime() / 1000;

    if (time - (+localStorage.getItem('time') / 1000) < +localStorage.getItem("tokenTime")) {
        return;
    }

    let response = await fetch('http://localhost:8080/refresh_token', {
        method: 'PUT',
        credentials: "same-origin"
    });
    if (response.ok) {
        localStorage.setItem('time', (+time * 100).toString());
    } else {
        window.location.replace('/authorization');
    }
}