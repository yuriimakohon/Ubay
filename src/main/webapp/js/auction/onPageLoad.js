document.addEventListener("DOMContentLoaded", async function () {
    if (await ref_token() || localStorage.getItem('role') == 1) {
        $('#btn-show_bid').addClass('hidden');
        $('.user-feedback').addClass('hidden');
    }
});
