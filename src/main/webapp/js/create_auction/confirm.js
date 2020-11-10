function onCreateAuction() {
    let title = $('#input-title').val();
    let desc = $('#textarea-description').val();
    let startPrice = $('#input-start_price').val();
    let maxPrice = $('#input-max_price').val();
    let startTime = $('#input-start_time').val();
    let duration = $('#range-duration').val();

    if (!title || !desc || !startPrice || !maxPrice || !startTime) {
        errEmptyFields();
    } else if (+maxPrice <= +startPrice) {
        errMaxPrice();
    } else {
        alert('Title: ' + title + '\nDesc: ' + desc + '\nsPrice: ' +
            startPrice  + '\nmPrice: ' + maxPrice + '\nsTime: ' +
            startTime + '\nDuration: ' + duration);
    }
}