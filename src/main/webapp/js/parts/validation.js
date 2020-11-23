const REGEX_LOGIN = new RegExp('^[A-Za-z0-9]{3,21}$');
const REGEX_PASS = new RegExp('^[A-Za-z0-9]{3,30}$');
const REGEX_LOT_TEXT = new RegExp('^[A-Za-z0-9- ,!]*$');

function check_categories() {
    let categories = '';

    $("input:checkbox[name=category]:checked").each(function(){
        categories += '-' + $(this).val();
    });

    return categories;
}

function checkPrices(startPrice, maxPrice) {
    if (+startPrice >= +maxPrice) {
        errMaxPrice();
        return false;
    } else if (+startPrice < 0 || +maxPrice < 0) {
        errNegativePrice()
        return false;
    } else if (+startPrice > 100_000 || +maxPrice > 100_000) {
        errPriceTooBig();
        return false;
    }
    return true;
}
