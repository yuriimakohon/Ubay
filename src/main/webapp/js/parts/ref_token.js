async function ref_token() {
    let response = await fetch('http://localhost:8080/refresh_token', {
        method: 'PUT',
        credentials: "same-origin"
    });
    if (response.ok) {} else {
        window.location.replace('/authorization');
    }
}