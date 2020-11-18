let title = $('#input-title');
let login = $('#input-login');
let collectibles = $('#category-collectibles');
let clothing = $('#category-clothing');
let technics = $('#category-technics');
let furniture = $('#category-furniture');
let household = $('#category-household');
let jewellery = $('#category-jewellery');
let active = $('#state-active');
let finished = $('#state-finished');
let not_started = $('#state-not_started');
let price = $('#start-price');
let pRate = $('#p-rate');
let rangeRate = $('#range-rate');

function onRangeRate() {
    pRate.text(rangeRate.val());
}

onRangeRate();


async function search() {
    let url = 'http://localhost:8080/api/search?'
    let categories = check_categories();
    let status = check_status();

    if (title.text() !== '')
        url = addParam(url, 'title', title.text());
    if (login.text() !== '')
        url = addParam(url, 'login', login.text());
    if (categories !== '')
        url = addParam(url, 'categories', categories);
    if (check_status() !== '')
        url = addParam(url, 'status', status);
    if (price.val() !== 0)
        url = addParam(url, 'price', price.val());
    url = addParam(url, 'rate', rangeRate.val());

    let response = await fetch(url, {
        method: 'GET',
        credentials: 'same-origin'
    });
}

function check_categories() {
    let categories = '';
    categories = add_check_box(categories, collectibles);
    categories = add_check_box(categories, technics);
    categories = add_check_box(categories, clothing);
    categories = add_check_box(categories, furniture);
    categories = add_check_box(categories, household);
    categories = add_check_box(categories, jewellery);
    return categories;
}

function check_status() {
    let status = ''
    status = add_check_box(status, active);
    status = add_check_box(status, finished);
    status = add_check_box(status, not_started);
    return status;
}

function add_check_box(categories, check_box) {
    if (check_box.is(':checked')) {
        categories += '-' + check_box.text();
    }
    return categories;
}

function addParam(url, param, value) {
    return url + param + '=' + value + '&';
}