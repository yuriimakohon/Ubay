function auctionHtmlGen(lotId, title, price, bidsCount, photo, status) {
    let html = ['        <div class="auction-item card ' + status + '">\n' +
    '            <img src="' + photo + '0.jpg" alt="auction-' + lotId + '" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div class="price">' + price + '</div>\n' +
    '            </div>\n' +
    '            <div class="control_block">\n'];

    if (status === 1) {
        html += '                <button class="btn" onclick="onAuctionEdit(' + lotId + ')">';
    }
    else {
        html += '                <button class="btn hidden">';
    }
    html += ['edit</button>\n' +
    '                <div class="bids_label">Bids:</div>\n' +
    '                <div class="bids_count">' + bidsCount + '</div>\n' +
    '            </div>\n' +
    '        </div>\n'];

    return html;
}

function auctionsGen(json) {
    let container = $('#auctions-container');

    json.forEach(el => {
        let title = el['title'];
        let price = el['price'];
        let lotId = el['lotId'];
        let photo = el['photo'];
        let status = el['status'];
        let bidsCount = el['bidNumber'];
        let auction = auctionHtmlGen(lotId, title, price, bidsCount, photo, status);
        container.append(auction);
    });
    onStatus(0);
}
