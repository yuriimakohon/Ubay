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
let errorStartTime2 = $('#errorStartTime2');
let durationTime = $('#duration');

document.getElementById('sub').addEventListener('click',  async function () {
    // validate all
    let regexpTitle = new RegExp('^([-a-zA-Z0-9]){3,}$');
    if (!regexpTitle.test(auctionTitle.val())) {
        errorTitle.css('display', 'block');
        return;
    } else {
        errorTitle.css('display', 'none');
    }

    let regexpDescription = new RegExp('^([-a-zA-Z0-9,!?]){3,}$');
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
    let st = new Date();
    st.setFullYear(parseInt(startTime.val().split("T")[0].split("-")[0]))
    st.setMonth(parseInt(startTime.val().split("T")[0].split("-")[1]));
    st.setDate(parseInt(startTime.val().split("T")[0].split("-")[2]));
    st.setHours(parseInt(startTime.val().split("T")[1].split(":")[0]));
    st.setMinutes(parseInt(startTime.val().split("T")[1].split(":")[1]));


    let currTime = new Date();
    currTime.setMonth(currTime.getMonth() + 1);
    if (st.getTime() <= currTime.getTime() + 60) {
        errorStartTime2.css('display', 'block');
        return;
    } else {
        errorStartTime2.css('display', 'none');
    }

    // create object for request
    let auction = {
        title: auctionTitle.val(),
        desc: desc.val(),
        startPrice: startPrice.val(),
        maxPrice: maxPrice.val(),
        startTime: st.getTime(),
        duration: durationTime.val()
    };
    // download files
    auction["images"] = await loadFiles(document.getElementById('forImage'));

    // post request
    let response = await fetch("http://localhost:8080/create_auction", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(auction)
    });
    // machining  response
    if (response.ok) {
        let text = await response.json();
        console.log('ok: ' + text);
    } else {
        console.log('error');
    }
})