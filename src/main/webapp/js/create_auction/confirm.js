async function onCreateAuction() {
    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    let startTime = new Date($('#input-start_time').val()).getTime();
    let duration = +$('#range-duration').val();
    let photos = document.getElementById('input-photos');

    if (!title || !desc || !startPrice || !maxPrice || !startTime || photos.files.length < 1) {
        errEmptyFields();
    } else if (!REGEX_LOT_TEXT.test(title)) {
        errTitleFormat();
    } else if (!REGEX_LOT_TEXT.test(desc)) {
        errDescriptionFormat();
    } else if (photos.files.length > 6) {
        errPhotoCount();
    } else if (+maxPrice <= +startPrice) {
        errMaxPrice();
    } else if (!checkMinTime(startTime)) {
        errStartTime();
    } else {
        //parse date
        let st = datetimeToData(startTime);

        let categories = check_categories()
        if (categories !== '') {
            categories = categories.slice(1, categories.length);
        }

        // create object for request
        let auction = {
            title: title,
            desc: desc,
            startPrice: startPrice,
            maxPrice: maxPrice,
            startTime: new Date().getTime() + 10_000, //TODO: manual user control set startTime
            duration: duration,
            categories: categories
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
            setErrorText('permission denied');
            showInfoText();
        }
    }
}

