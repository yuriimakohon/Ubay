function bidHtmlGen(title, bid, lotId, bidId, photo) {
    return ['<div class="bid-item card">\n' +
    '            <img src="' + photo + '" alt="bid-" onclick="onAuction(' + lotId + ')">\n' +
    '            <div class="info_block">\n' +
    '                <div class="title" onclick="onAuction(' + lotId + ')">' + title + '</div>\n' +
    '                <div class="price">' + bid + '</div>\n' +
    '            </div>\n' +
    '            <div class="control_block">\n' +
    '                <button class="btn" onclick="onRebid(' + lotId + ')">Rebid</button>\n' +
    '                <button class="btn btn-red" onclick="onBidDelete(' + bidId + ')">Delete</button>\n' +
    '            </div>\n' +
    '        </div>'];
}

function bidsGen(json) {
    let container = $('#bids-container');

    // json.lots.forEach(el => {
    //     let parsed = JSON.parse(el);
    //     let title = parsed['title'];
    //     let price = parsed['price'];
    //     let lotId = parsed['lotId'];
    //     let photo = parsed['photo']
    //     let bidsCount = 5;
        let auction = bidHtmlGen('Test lot blin', 25.54, 111, 888, "/resources/test.png");
        let auction2 = bidHtmlGen('Test lot blin 2', 1337, 222, 999, "/resources/test2.jpeg");

        container.append(auction);
        container.append(auction2);
    // });
}