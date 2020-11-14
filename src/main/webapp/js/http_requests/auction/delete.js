async function onDeleteAuction() {
    let response = await fetch('http://localhost:8080/api/auction/' + localStorage.getItem('lotId'), {
        method: 'DELETE',
        credentials: 'same-origin'
    });
    // machining  response
    if (response.ok) {
        window.location.replace('/auctions');
    } else {
        console.log(await response.text());
    }
}