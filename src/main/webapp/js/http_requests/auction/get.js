
/*
* return object auction or null
* */

async function get_auction() {
    let response = await fetch('http://localhost:8080/get_auction?lotId=' + localStorage.getItem('lotId'), {
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