document.addEventListener("DOMContentLoaded", async function () {
    let response = await fetch('http://localhost:8080//get_auctions', {
        method: 'GET',
    });
    if (response.ok) {
        let respJson = await response.json();
        lotsGen(respJson);
    } else {
        console.log("error");
    }
});