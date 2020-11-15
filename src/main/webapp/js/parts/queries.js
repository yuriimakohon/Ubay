function onAuction(id) {
    localStorage.setItem('lotId', id);
    window.location.replace('/auction/' + id);
}

function onAuctionEdit(id) {
    localStorage.setItem("lotId", id);
    window.location.replace('/edit_auction');
}