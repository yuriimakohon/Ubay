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

async function onPlaceBid(currentBid) {
    if (inputBid.val() <= currentBid) {
        alert('Your bid can\'t be lower than current highest bid');
    } else {
        alert("Your bid " + inputBid.val() + "$ was successfully placed!");
    }
}
