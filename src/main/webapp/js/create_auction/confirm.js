async function onCreateAuction() {
    await ref_token();

    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    let startTime = $('#input-start_time').val();
    let duration = $('#range-duration').val();
    let photos = document.getElementById('input-photos');

    if (!title || !desc || !startPrice || !maxPrice || !startTime || photos.files.length < 1) {
        errEmptyFields();
    } else if (photos.files.length > 6) {
        errPhotoCount();
    } else if (+maxPrice <= +startPrice) {
        errMaxPrice();
    } else if (!checkMinTime(startTime)) {
        errStartTime();
    } else {
        //parse date
        let st = datetimeToData(startTime);

        // create object for request
        let auction = {
            title: title,
            desc: desc,
            startPrice: startPrice,
            maxPrice: maxPrice,
            startTime: st.getTime(),
            duration: duration
        };
        // download files
        auction["images"] = await loadFiles(photos);

        // post request
        let response = await fetch("http://localhost:8080/api/auction", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(auction)
        });
        // machining  response
        if (response.ok) {
            window.location.replace("/auctions");
        } else {
            if (response.status === 406) {
                setErrorText('permission denied');
            } else {
                setErrorText('error');
            }
            showInfoText();
        }
    }
}
