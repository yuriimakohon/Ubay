document.addEventListener("DOMContentLoaded", async function loadMyAuctions() {
    await ref_token();

    console.log("i am here");

    let response = await fetch('http://localhost:8080/api/auction', {
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