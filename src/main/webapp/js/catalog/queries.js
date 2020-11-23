document.addEventListener("DOMContentLoaded", async function () {
    let response = await  fetch('http://localhost:8080/api/search?status=1-2', {
        method: 'GET'
    });
    if (response.ok) {
        let lots = await response.json();
        if (lots != null) {
            lotsGen(lots);
        }
    } else {
        alert('hm what is wrong ? Please check internet connection');
    }

});

function onCategory(category) {
    onForward('http://localhost:8080/results?categories=' + category);
}