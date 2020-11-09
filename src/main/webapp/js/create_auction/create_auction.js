
document.getElementById('sub').addEventListener('click',  async function () {
    // validate all
    let regexpTitle = new RegExp('^([-a-zA-Z0-9]){3,}$');

    let regexpDescription = new RegExp('^([-a-zA-Z0-9,!?]){3,}$');





    // parse date
    let st = new Date();
    st.setFullYear(parseInt(startTime.val().split("T")[0].split("-")[0]))
    st.setMonth(parseInt(startTime.val().split("T")[0].split("-")[1]));
    st.setDate(parseInt(startTime.val().split("T")[0].split("-")[2]));
    st.setHours(parseInt(startTime.val().split("T")[1].split(":")[0]));
    st.setMinutes(parseInt(startTime.val().split("T")[1].split(":")[1]));


    let currTime = new Date();
    currTime.setMonth(currTime.getMonth() + 1);

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
    }
})