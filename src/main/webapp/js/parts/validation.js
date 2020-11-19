const REGEX_LOGIN = new RegExp('^[A-Za-z0-9]{3,21}$');
const REGEX_PASS = new RegExp('^[A-Za-z0-9]{3,30}$');

const REGEX_LOT_TEXT = new RegExp('^[A-Za-z0-9- ,!]*$');

function check_category(category, name_c) {
    if (category.is(':checked')) {
        return '-' + name_c;
    }
    return '';
}

function check_categories() {
    let collectibles = $('#checkbox-collectibles');
    let technics = $('#checkbox-technics');
    let furniture = $('#checkbox-furniture');
    let clothing = $('#checkbox-clothing');
    let household = $('#checkbox-household');
    let jewellery = $('#checkbox-jewellery');
    let categories = '';

    categories += check_category(collectibles, 'collectibles');
    categories += check_category(technics, 'technics');
    categories += check_category(clothing, 'clothing');
    categories += check_category(furniture, 'furniture');
    categories += check_category(household, 'household');
    categories += check_category(jewellery, 'jewellery');
    return categories;
}