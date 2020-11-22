document.addEventListener("DOMContentLoaded", async function () {
    let lots = await get_auctions();
    if (lots != null) {
        lotsGen(lots);
    }
});

function onCategory(category) {
    onForward('http://localhost:8080/results?categories=' + category);
}