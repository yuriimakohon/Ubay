let prices = $('#prices');
let btnShowBid = $('#btn-show_bid');
let bidContainer = $('#bid-container');
let inputBid = $('#input-bid');

$('#your_bid input').after(' $');

function onShowBid() {
    prices.addClass('hidden');
    btnShowBid.addClass('hidden');
    bidContainer.removeClass('hidden');
}

function onCancelBid() {
    prices.removeClass('hidden');
    btnShowBid.removeClass('hidden');
    bidContainer.addClass('hidden');
}

async function onPlaceBid(lotId, currentBid) {
    if (+inputBid.val() <= +currentBid) {
        alert('Your bid can\'t be lower than current highest bid');
    } else {
        let bid = {
          lotId: lotId,
          bid: inputBid.val()
        };

        let response = await fetch('http://localhost:8080/api/bid', {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(bid)
        });

        let message = await response.text();

        if (response.ok) {
            if (response.status === 200) {
                console.log('Your bid ' + inputBid.val() + '$ was successfully placed!');
            } else {
                console.log('you win')
            }

            localStorage.setItem('balance', (+localStorage.getItem('balance') - +inputBid.val()).toString())
            let balance = Number(localStorage.getItem('balance')).toFixed(2);
            $('#counter-balance').text(balance);

            let bids_count = $('#info-title-bids_count');
            bids_count.text((+bids_count.text() + 1).toString());
            $('#info-title-current_price').text(inputBid.val() + " ");
        } else {
            console.log(message)
        }
        prices.removeClass('hidden');
        btnShowBid.removeClass('hidden');
        bidContainer.addClass('hidden');
    }
}
