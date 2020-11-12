async function user_get() {
    let response = await fetch('http://localhost:8080/user/get', {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        let user = await response.json();
        console.log(user);
        return user;
    } else {
        return null;
    }
}