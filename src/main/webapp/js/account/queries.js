document.addEventListener("DOMContentLoaded", async function () {
    let user = await user_get();

    if (user != null) {
        $('#input-login')
                 .val(user.login);
        localStorage.setItem('balance', user.balance);
    }
});