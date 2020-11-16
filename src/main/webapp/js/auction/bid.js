let prices = $('#prices');
let btnShowBid = $('#btn-show_bid');
let bidContainer = $('#bid-container');

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