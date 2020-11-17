document.addEventListener('DOMContentLoaded', async function () {
    // await ref_token();
    let role = localStorage.getItem('role');
    let balance = localStorage.getItem('balance');
    if (balance == null) {
        localStorage.setItem('balance', '0');
    }

    if (role == null) {
        $('#item-sign_up').removeClass('hidden');
    } else {
        $('#item-account').removeClass('hidden');
        $('#item-balance').removeClass('hidden');
        $('#counter-balance').text(localStorage.getItem('balance'))
        if (+role === 1) { // auctioneer
            $('#item-auction').removeClass('hidden')
        } else { // bidder
            $('#item-bids').removeClass('hidden')
        }
    }
});

function onAdvancedSearch() {
    window.location.replace("http://localhost:8080/search");
}
