async function editAuction() {
    let response = await fetch('http://localhost:8080/auction/edit', {
        method: 'PUT',
        credentials: "same-origin",
        headers: {

        }
    })
}