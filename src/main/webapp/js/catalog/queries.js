document.addEventListener("DOMContentLoaded", async function () {
    let cook = parseCookie();
    console.log(cook);

    // let req = {
    //     id: cook.id,
    //     token: cook.token
    // }

    let response = await fetch('http://localhost:8080/catalog/get_info', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        // body: JSON.stringify(req)
    });
    if (response.ok) {
        console.log('response ok')
    } else {
        console.log("error")
    }
});