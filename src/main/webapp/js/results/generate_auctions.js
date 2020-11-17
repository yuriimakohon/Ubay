function auctionHtmlGen(lotId, title, price, bidsCount, photo) {
    return ['        <div class="auction-item card">\n' +
    '            <img src="' + photo + '0.jpg" alt="auction-' + lotId + '" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div class="price">' + price + '</div>\n' +
    '            </div>\n' +
    '        </div>\n'];
}

function resultAuctionsGen(json) {
    let container = $('#auctions-container');

    json.lots.forEach(el => {
        let parsed = JSON.parse(el);
        let title = parsed['title'];
        let price = parsed['price'];
        let lotId = parsed['lotId'];
        let photo = parsed['photo']
        let bidsCount = 5;
        let auction = auctionHtmlGen(lotId, title, price, bidsCount, photo);

        container.append(auction);
    });
}