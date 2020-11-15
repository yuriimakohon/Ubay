document.addEventListener("DOMContentLoaded", async function () {
    let lots = await get_auctions();
    if (lots != null) {
        lotsGen(lots);
    }
});
