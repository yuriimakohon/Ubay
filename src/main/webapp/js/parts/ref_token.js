async function ref_token() {
    if (getElementOfCookie('token') != null) {
        return true;
    }

    let response = await fetch('http://localhost:8080/refresh_token', {
        method: 'PUT',
        credentials: "same-origin"
    });
    return response.ok;
}