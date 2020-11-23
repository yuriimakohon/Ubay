function onRebid(id) {
    window.location.replace('/auction/' + id);
}

async function onBidDelete(id, yourBid) {
    let response = await fetch('http://localhost:8080/api/bid/' + id,  {
        method: 'DELETE',
        credentials: 'same-origin'
    });

    if (response.ok) {
        console.log('deleted');
        localStorage.setItem('balance', (+localStorage.getItem('balance') + yourBid));
        location.reload();
    } else {
        console.log('permission denied');
    }
}