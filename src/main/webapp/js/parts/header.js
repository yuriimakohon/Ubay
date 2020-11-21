let account = $('#item-account');
let balance = $('#item-balance');
let auction = $('#item-auction');
let bid = $('#item-bids');


document.addEventListener('DOMContentLoaded', async function () {
    if (!await ref_token()) {
        $('#img-account').attr('src', '/resources/user.svg');
        $('#item-sign_up').removeClass('hidden');
        account.removeClass('hidden');
        balance.removeClass('hidden');
        auction.removeClass('hidden')
        bid.removeClass('hidden');

        account.addClass('hidden');
        balance.addClass('hidden');
        auction.addClass('hidden')
        bid.addClass('hidden');
    } else {
        let role = localStorage.getItem('role');
        let balance = localStorage.getItem('balance');
        let avatar = localStorage.getItem('avatar');

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
    }
});

function onAdvancedSearch() {
    window.location.replace('/search');
}

function onSearch() {
    let title = $('#text_input-search');

    let url = '?';
    if (title.val() !== '') {
        url += 'title=' + title.val();
    }
    window.location.replace('/results' + url);
}
