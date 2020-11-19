function auctionHtmlGen(lotId, title, price, bidsCount, photo) {
    return ['        <div class="auction-item card">\n' +
    '            <img src="' + photo + '0.jpg" alt="auction-' + lotId + '" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div class="price">' + price + '</div>\n' +
    '            </div>\n' +
    '            <div class="control_block">\n' +
    '                <button class="btn" onclick="onAuctionEdit(' + lotId + ')">edit</button>\n' +
    '                <div class="bids_label">Bids:</div>\n' +
    '                <div class="bids_count">' + bidsCount + '</div>\n' +
    '            </div>\n' +
    '        </div>\n'];
}

function auctionsGen(json) {
    let container = $('#auctions-container');

    json.lots.forEach(el => {
        let title = el['title'];
        let price = el['price'];
        let lotId = el['lotId'];
        let photo = el['photo']
        let bidsCount = 5;
        let auction = auctionHtmlGen(lotId, title, price, bidsCount, photo);

        container.append(auction);
    });
}
