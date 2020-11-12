document.addEventListener("DOMContentLoaded", async function () {
    let response = await fetch('http://localhost:8080/get_auction?lotId=' + localStorage.getItem('lotId'), {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        let json = await response.json();
        let lot = JSON.parse(json.lot);
        let rd = $('#range-duration');

        $('#input-title').val(lot.title);
        $('#textarea-description').val(lot.description);
        // $('#input-photos').val(lot.photo);
        $('#input-start_price').val(lot.price);
        $('#input-max_price').val(lot.maxPrice);
        // $('#input-start_time').val(lot.startTime);
        rd.val((+lot.duration - +lot.startTime) / 24 / 3600);
        $('#p-duration').text(rd.val());
    } else {
        console.log(await response.text());
    }
})