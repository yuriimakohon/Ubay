console.log("Hello World!");

document.getElementById('sub').addEventListener('click',  async function () {
    console.log('tl: ' + document.getElementById('title_auction').value);
    console.log('dc: ' + document.getElementById('description').value);
    console.log('sp: ' + document.getElementById('start_price').value);
    console.log('mp: ' + document.getElementById('max_price').value);
    console.log('st: ' + document.getElementById('start_time').value);
    console.log('dr: ' + document.getElementById('duration').value);

    let formData = new FormData();
    for (let i = 0; document.getElementById('forImage').files[i] != null; i++ ) {
        formData.append(document.getElementById('forImage').files[i].name, document.getElementById('forImage').files[i])
    }

    let response = await fetch("http://localhost:8080/ubay/create_auctions", {
        method: 'POST',
        // enctype: 'mutlipart/form-data'
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        body: formData
    })
    if (response.ok) {
        let text = await response.text();
        console.log('ok: ' + text);
    } else {
        console.log('error');
    }
})