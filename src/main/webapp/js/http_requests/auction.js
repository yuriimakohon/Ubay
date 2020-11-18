

async function onDeleteAuction() {
    let response = await fetch('http://localhost:8080/api/auction/' + localStorage.getItem('lotId'), {
        method: 'DELETE',
        credentials: 'same-origin'
    });
    // machining  response
    if (response.ok) {
        window.location.replace('/auctions');
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
        let json = await response.json()
        return JSON.parse(json.lot);
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
    let startTime = $('#input-start_time').val();
    let duration = $('#range-duration').val();
    // let photos = document.getElementById('input-photos');

    if (!title || !desc || !startPrice || !maxPrice || !startTime
        // || photos.files.length < 1
    ) {
        errEmptyFields();
        // } else if (photos.files.length > 6) {
        //     errPhotoCount();
    } else if (+maxPrice <= +startPrice) {
        errMaxPrice();
    } else {
        //parse date
        let st = new Date();
        st.setFullYear(parseInt(startTime.split("T")[0].split("-")[0]))
        st.setMonth(parseInt(startTime.split("T")[0].split("-")[1]) - 1);
        st.setDate(parseInt(startTime.split("T")[0].split("-")[2]));
        st.setHours(parseInt(startTime.split("T")[1].split(":")[0]));
        st.setMinutes(parseInt(startTime.split("T")[1].split(":")[1]));

        // create object for request
        let auction = {
            title: title,
            desc: desc,
            startPrice: startPrice,
            maxPrice: maxPrice,
            startTime: st.getTime(),
            duration: duration,
            status: 1
        };
        // download files
        // auction["images"] = await loadFiles(photos);

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
            window.location.replace("/auctions");
        } else {
            setErrorText('permission denied');
            showInfoText();
        }
    }
}