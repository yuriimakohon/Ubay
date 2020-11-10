function onCreateAuction() {
    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    let startTime = $('#input-start_time').val();
    let duration = $('#range-duration').val();
    let photos = document.getElementById('input-photos');

    if (!title || !desc || !startPrice || !maxPrice || !startTime || photos.files.length < 1) {
        errEmptyFields();
    } else if (photos.files.length > 6) {
        errPhotoCount();
    } else if (+maxPrice <= +startPrice) {
        errMaxPrice();
    } else {
        alert('Title: ' + title + '\nDesc: ' + desc + '\nsPrice: ' +
            startPrice  + '\nmPrice: ' + maxPrice + '\nsTime: ' +
            startTime + '\nDuration: ' + duration);
    }
}