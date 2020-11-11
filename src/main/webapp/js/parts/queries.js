function onAuction(id) {
    alert('REQUEST: Open Auction: ' + id);
}

function onAuctionEdit(id) {
    localStorage.setItem("lotId", id);
    window.location.replace('/edit_auction');
}