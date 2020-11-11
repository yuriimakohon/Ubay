document.addEventListener("DOMContentLoaded", async function loadMyAuctions() {
    let response = await fetch('http://localhost:8080/get_my_auctions', {
        method: 'GET',
        credentials: "same-origin"
    });
    if (response.ok) {
        let json = await response.json();
        auctionsGen(json);
    } else {
        console.log("what is wrong ?");
    }
});