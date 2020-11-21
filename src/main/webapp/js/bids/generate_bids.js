function bidHtmlGen(title, bid, lotId, bidId, photo, status) {
    let gen = ['<div class="bid-item card">\n' +
    '            <img src="' + photo + '0.jpg" alt="bid-" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div class="price">' + bid + '</div>\n' +
    '            </div>\n' +
    '            <div class="control_block">\n'];

    if (status === 2) {
        gen += ['<div class="control_block">\n' +
            '                <button class="btn" onclick="onRebid(' + lotId + ')">Rebid</button>\n' +
            '                <button class="btn btn-red" onclick="onBidDelete(' + bidId + ')">Delete</button>\n' +
            '            </div>\n'];
    } else if (status === 1) {
        gen += ['<div class="control_block">\n' +
        '             WIN' +
        '            </div>\n'];
    } else {
        gen += ['<div class="control_block">\n' +
        '             LOSE' +
        '            </div>\n'];
    }
    gen += '        </div>';
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