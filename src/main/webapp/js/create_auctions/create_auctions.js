let auctionTitle = $('#title_auction');
let errorTitle = $('#errorTitle');
let desc = $('#description');
let errorDesc = $('#errorDescription');
let startPrice = $('#start_price');
let errorStartPrice = $('#errorStartPrice');
let maxPrice = $('#max_price');
let errorMaxPrice = $('#errorMaxPrice');
let startTime = $('#start_time');
let errorStartTime = $('#errorStartTime');
let durationTime = $('#duration');


document.getElementById('sub').addEventListener('click',  async function () {
    // validate all
    let regexpTitle = new RegExp('([-a-zA-Z0-9]){3,}');
    if (!regexpTitle.test(auctionTitle.val())) {
        errorTitle.css('display', 'block');
        return;
    } else {
        errorTitle.css('display', 'none');
    }

    let regexpDescription = new RegExp('([-a-zA-Z0-9,!?]){3,}');
    if (!regexpDescription.test(desc.val())) {
        errorDesc.css('display', 'block');
        return;
    } else {
        errorDesc.css('display', 'none');
    }

    if (startPrice.val() === '') {
        errorStartPrice.css('display', 'block');
        return;
    } else {
        errorStartPrice.css('display', 'none');
    }

    if (maxPrice.val() === '' || parseInt(maxPrice.val()) <= parseInt(startPrice.val())) {
        errorMaxPrice.css('display', 'block');
        return;
    } else {
        errorMaxPrice.css('display', 'none');
    }

    if (startTime.val() === '') {
        errorStartTime.css('display', 'block');
        return;
    } else {
        errorStartTime.css('display', 'none');
    }

    // parse date
    console.log(startTime.val());
    let date = new Date();
    date.setFullYear(parseInt(startTime.val().split("T")[0].split("-")[0]))
    date.setMonth(parseInt(startTime.val().split("T")[0].split("-")[1]));
    date.setDate(parseInt(startTime.val().split("T")[0].split("-")[2]));
    date.setHours(parseInt(startTime.val().split("T")[1].split(":")[0]));
    date.setMinutes(parseInt(startTime.val().split("T")[1].split(":")[1]));
    date.setSeconds(0)
    date.setMilliseconds(0);
    console.log(new Date().toLocaleString());
    console.log('sec: ' + date.getSeconds());
    console.log('min: ' + date.getMinutes());
    console.log('hour: ' + date.getHours());
    console.log('day: ' + date.getDate());
    console.log('month: ' + date.getMonth());
    console.log('year: ' + date.getFullYear());
    console.log(date.getTime());

    let date2 = new Date();
    console.log('sec: ' + date2.getSeconds());
    console.log('min: ' + date2.getMinutes());
    console.log('hour: ' + date2.getHours());
    console.log('day: ' + date2.getDate());
    console.log('month: ' + date2.getMonth());
    console.log('year: ' + date2.getFullYear());
    date2.setMonth(11);
    console.log(date2.getTime());




    // create object for request
    let auction = {
        title: auctionTitle.val(),
        desc: desc.val(),
        startPrice: startPrice.val(),
        maxPrice: maxPrice.val(),
        startTime: startTime.val(),
        duration: durationTime.val()
    };
    // download files
    auction["images"] = await loadFiles(document.getElementById('forImage'));

    // post request
    let response = await fetch("http://localhost:8080/ubay/create_auctions", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(auction)
    });
    // machining  response
    if (response.ok) {
        let text = await response.text();
        console.log('ok: ' + text);
    } else {
        console.log('error');
    }
})