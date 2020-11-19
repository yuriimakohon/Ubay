function lotHtmlGen(lotId, title, price, photo) {
    return ['                    <div class="card auctions-item" onclick="onAuction(' + lotId + ')">\n' +
    '                        <img src="' + photo + '0.jpg">\n' +
    '                        <div class="text">\n' +
    '                            <div class="title">\n' +
    '                                <p>' + title + '</p>\n' +
    '                            </div>\n' +
    '                            <p class="price">' + price + '</p>\n' +
    '                        </div>\n' +
    '                    </div>\n'];
}

function lotsGen(json) {
    let container = $('#auctions-container');

    json.forEach(el => {
        let title = el['title'];
        let price = el['price'];
        let lotId = el['lotId'];
        let photo = el['photo'];
        let lot = lotHtmlGen(lotId, title, price, photo);

        container.append(lot);
    });
}
