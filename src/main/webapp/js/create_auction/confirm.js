document.addEventListener("DOMContentLoaded", async function () {
    if (await ref_token())
        window.location.replace('/authorization');
});

async function onCreateAuction() {
    if (await ref_token())
        window.location.replace('/authorization');

    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    let startTime = $('#input-start_time').val();
    let duration = +$('#range-duration').val();
    // let duration = addDays(datetimeToData(startTime), +$('#range-duration').val());  //TODO: manual user control ^^^^^
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
            startTime: st.getTime(),
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


function check_categories() {
    let collectibles = $('#checkbox-collectibles');
    let technics = $('#checkbox-technics');
    let furniture = $('#checkbox-furniture');
    let clothing = $('#checkbox-clothing');
    let household = $('#checkbox-household');
    let jewellery = $('#checkbox-jewellery');
    let categories = '';

    categories += check_category(collectibles, 'collectibles');
    categories += check_category(technics, 'technics');
    categories += check_category(clothing, 'clothing');
    categories += check_category(furniture, 'furniture');
    categories += check_category(household, 'household');
    categories += check_category(jewellery, 'jewellery');
    return categories;
}
