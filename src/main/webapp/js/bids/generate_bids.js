function bidHtmlGen(title, bid, lotId, bidId, photo, status) {
    let gen = ['<div class="bid-item card '];

    gen += ['<div class="bid-item card">\n' +
    '            <img src="' + photo + '0.jpg" alt="bid-" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div id="your_price" class="price ']

    if (status === 2) {
        gen += ['price-loses'];
    } else if(status === 3) {
        gen += ['price-won'];
    }

    gen += ['">' + bid + '</div>\n' +
    '            </div>\n'];
    if (status === 2) {
        gen += ['<div class="control_block">\n' +
            '                <button class="btn" onclick="onRebid(' + lotId + ')">Rebid</button>\n' +
            '                <button class="btn btn-red" onclick="onBidDelete(' + bidId + ',' +  bid + ')">Delete</button>\n' +
            '            </div>\n'];
    }
    return gen;
}

function bidsGen(json) {
    let container = $('#bids-container');

    json.forEach(el => {
        let lot = el['lot'];
        let title = lot['title'];
        let price = el['price'];
        let lotId = el['lotId'];
        let photo = lot['photo'];
        let id = el['id'];
        let status = el['statusOfBid'];
        let auction = bidHtmlGen(title, price, lotId, id, photo, status);

        container.append(auction);
    });
}