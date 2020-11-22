function onAuction(id) {
    localStorage.setItem('lotId', id);
    onForward('/auction/' + id);
}

function onAuctionEdit(id) {
    localStorage.setItem("lotId", id);
    onForward('/edit_auction');
}

function onForward(path) {
    window.location.assign(path);
}