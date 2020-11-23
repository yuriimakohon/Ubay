

async function onDeleteAuction() {
    let response = await fetch('http://localhost:8080/api/auction/' + localStorage.getItem('lotId'), {
        method: 'DELETE',
        credentials: 'same-origin'
    });
    // machining  response
    if (response.ok) {
        onForward('/auctions');
    } else {
        setErrorText('permission denied');
        showInfoText();
    }
}

/*
* return object auction or null
* */


async function get_auction() {
    let response = await fetch('http://localhost:8080/api/auction/' + localStorage.getItem('lotId'), {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        return await response.json();
    } else {
        return null;
    }
}

async function get_auctions() {
    let response = await fetch('http://localhost:8080/api/auction', {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        return await response.json();
    } else {
        return null;
    }
}

async function onSaveChanges() {
    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    // let startTime = new Date($('#input-start_time').val()).getTime();
    let duration = +$('#range-duration').val();

    if (!title || !desc || !startPrice || !maxPrice) {
        errEmptyFields();
    } else if (!REGEX_LOT_TEXT.test(title)) {
        errTitleFormat();
    } else if (!checkPrices(startPrice, maxPrice)) {
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
            startPrice: Number(startPrice).toFixed(2),
            maxPrice: Number(maxPrice).toFixed(2),
            startTime: st.getTime(),
            duration: duration,
            categories: categories
        };

        // post request
        let response = await fetch('http://localhost:8080/api/auction/' + localStorage.getItem('lotId'), {
            method: 'PUT',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(auction)
        });
        // machining  response
        if (response.ok) {
            onForward("/auctions");
        } else {
            setErrorText('permission denied');
            showInfoText();
        }
    }
}