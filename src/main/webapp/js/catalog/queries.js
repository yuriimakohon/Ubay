document.addEventListener("DOMContentLoaded", async function () {
    let response = await fetch('http://localhost:8080/auction/get_all', {
        method: 'GET',
    });
    if (response.ok) {
        let respJson = await response.json();
        lotsGen(respJson);
    } else {
        console.log("error");
    }
});
