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
    let date = new Date();
    date.setHours(parseInt(startTime.val().split("T")[1].split(":")[0]));
    date.setMinutes(parseInt(startTime.val().split("T")[1].split(":")[1]));
    date.setFullYear(parseInt(startTime.val().split("T")[0].split("-")[0]), parseInt(startTime.val().split("T")[0].split("-")[1]), parseInt(startTime.val().split("T")[0].split("-")[2]))
    date.setSeconds(0)
    date.setMilliseconds(0);
    let second = date.getTime();
    console.log(second);

    let date2 = new Date();
    console.log(date2.getTime());



    // validate all

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