document.addEventListener("DOMContentLoaded", async function loadAuctions() {
    let url = '/api/search?' + window.location.href.split('?')[1];

    let response = await fetch(url, {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        let json = await response.json();
        resultAuctionsGen(json);
    } else {
        console.log('response false');
    }
});