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
        let bidsCount = el['bidNumber'];;
        let auction = auctionHtmlGen(lotId, title, price, bidsCount, photo, status);
        container.append(auction);
    });
    $('#checkbox-all').prop('checked', true);
    onAuctionAll();
}

function onAuctionAll() {
    let allBox = $('.auction-item');
    let checkBoxAll = $('#checkbox-all');

    if (checkBoxAll.is(':checked')) {
        $('#checkbox-notActive').prop('checked', false);
        $('#checkbox-active').prop('checked', false);
        $('#checkbox-finished').prop('checked', false);
        allBox.each(function () {
            $(this).removeClass('hidden');
        });
    }
}

function onStatus() {
    let allBox = $('.auction-item');
    let checkBoxAll = $('#checkbox-all');
    checkBoxAll.prop('checked', false)


    allBox.each(function () {
        $(this).removeClass('hidden');
        $(this).addClass('hidden');
    });

    if ($('#checkbox-notActive').is(':checked')) {
        $('.1').each(function () {
            $(this).removeClass('hidden');
        });
    }
    if ($('#checkbox-active').is(':checked')) {
        $('.2').each(function () {
            $(this).removeClass('hidden');
        });
    }
    if ($('#checkbox-finished').is(':checked')) {
        $('.3').each(function () {
            $(this).removeClass('hidden');
        });
    }
}
