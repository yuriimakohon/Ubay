document.addEventListener("DOMContentLoaded", async function () {
    let bid_container = $('#btn-bid-container');

    if (bid_container.hasClass('end')) {

        return;
    }

    if (!await ref_token() || localStorage.getItem('role') === '1') {
        $('.user-feedback').addClass('hidden');
    } else {
        bid_container.removeClass('hidden');
    }
});
