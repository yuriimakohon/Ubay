let title = $('#input-title');
let login = $('#input-login');
let collectibles = $('#category-collectibles input');
let clothing = $('#category-clothing input');
let technics = $('#category-technics input');
let furniture = $('#category-furniture input');
let household = $('#category-household input');
let jewellery = $('#category-jewellery input');
let active = $('#state-active input');
let finished = $('#state-finished input');
let not_started = $('#state-not_started input');
let price = $('#max-price');

async function search() {
    let url = 'http://localhost:8080/api/search?'
    let categories = check_categories();
    let status = check_status();

    if (title.val() !== '') {
        url = addParam(url, 'title', title.val());
    }
    if (login.val() !== '') {
        url = addParam(url, 'login', login.val());
    }
    if (categories !== '') {
        url = addParam(url, 'categories', categories + '-');
    }
    if (status !== '') {
        url = addParam(url, 'status', status);
    }
    if (price.val() !== '') {
        url = addParam(url, 'price', price.val());
    }
    url = addParam(url, 'rate', rangeRate.val());

    let response = await fetch(url, {
        method: 'GET',
        credentials: 'same-origin'
    });
    if (response.ok) {
        console.log('response ok')
    } else {
        console.log('response false')
    }
}

function check_categories() {
    let categories = '';
    if (collectibles.is(':checked')) {
        categories += '-collectibles';
    }
    if (technics.is(':checked')) {
        categories += '-technics';
    }
    if (clothing.is(':checked')) {
        categories += '-clothing';
    }
    if (furniture.is(':checked')) {
        categories += '-furniture';
    }
    if (household.is(':checked')) {
        categories += '-household';
    }
    if (jewellery.is(':checked')) {
        categories += '-jewellery';
    }
    return categories;
}

function check_status() {
    let status = ''
    if (active.is(':checked')) {
        status += '-2';
    }
    if (finished.is(':checked')) {
        status += '-3';
    }
    if (not_started.is(':checked')) {
        status += '-1';
    }
    return status;
}

function addParam(url, param, value) {
    return url + param + '=' + value + '&';
}