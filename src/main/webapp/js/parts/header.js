document.addEventListener('DOMContentLoaded', async function () {
    // await ref_token();
    let role = localStorage.getItem('role');
    let balance = localStorage.getItem('balance');
    let avatar =  localStorage.getItem('avatar');

    if (balance == null) {
        localStorage.setItem('balance', '0');
    }

    if (role == null) {
        $('#item-sign_up').removeClass('hidden');
    } else {
        if (avatar !== undefined) {
            $('#img-account').attr('src', avatar);
        } else {
            $('#img-account').attr('src', '/resources/user.svg');
        }
        $('#item-account').removeClass('hidden');
        $('#item-balance').removeClass('hidden');
        let balance = Number(localStorage.getItem('balance')).toFixed(2);
        $('#counter-balance').text(balance);
        if (+role === 1) { // auctioneer
            $('#item-auction').removeClass('hidden')
        } else { // bidder
            $('#item-bids').removeClass('hidden')
        }
    }
});
