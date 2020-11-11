document.addEventListener("DOMContentLoaded", async function () {
   let response = await fetch('http://localhost:8080/user/get', {
       method: 'GET',
       credentials: "same-origin"
   });

    if (response.ok) {
        console.log("ok");
        let text = JSON.parse(await response.text());
        $('#input-login')
            .val(text.login);
    } else {
        console.log("error get account");
    }
});