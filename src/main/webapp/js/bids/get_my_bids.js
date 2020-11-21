document.addEventListener("DOMContentLoaded", async function getMyBids() {
     let response = await fetch('http://localhost:8080/api/bid',{
         method: 'GET',
         credentials: 'same-origin'
     })

    if (response.ok) {
        bidsGen(await response.json());
    } else {
        console.log(await response.text());
    }
});