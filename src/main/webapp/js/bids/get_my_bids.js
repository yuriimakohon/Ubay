document.addEventListener("DOMContentLoaded", async function getMyBids() {
    if (await ref_token()) {
        window.location.replace('/authorization');
    }

    bidsGen('');
});