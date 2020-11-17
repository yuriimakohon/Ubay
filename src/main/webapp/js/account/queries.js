document.addEventListener("DOMContentLoaded", async function () {
    await ref_token();

    let user = await user_get();

    if (user != null) {
        $('#input-login')
                 .val(user.login);
        localStorage.setItem('balance', user.balance);
    }
});