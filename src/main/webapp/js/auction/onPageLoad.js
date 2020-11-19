document.addEventListener("DOMContentLoaded", async function () {
    if (await ref_token()) {
        $('#btn-show_bid').addClass('hidden');
        $('.user-feedback').addClass('hidden');
    }
});
