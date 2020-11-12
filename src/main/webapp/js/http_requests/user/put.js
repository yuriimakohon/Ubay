async function addBalance() {
    await changeBalance($('#input-dollar').val());
}

async function changeBalance(dollars) {
    let user = {
        balance: dollars
    }

    let response = await fetch('http://localhost:8080/user/change_balance', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    if (response.ok) {
        let cb = $('#counter-balance');
        cb.text(+cb.text() + +dollars);
        localStorage.setItem('balance', cb.text());
    } else {
        console.log(await response.text());
    }
}