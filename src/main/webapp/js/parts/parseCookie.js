function getElementOfCookie(nameElement) {
    let elements = document.cookie.split(";");

    for (let i = 0; i < elements.length; i++) {
        let values = elements[i].split("=");

        if (values[i] === nameElement) {
            return values[i + 1];
        }
    }
    return null
}

function addToCookie(key, value) {
    document.cookie += key + "=" + value + ';'
}

function parseCookie() {
    let elements = document.cookie.split(";");
    let obj = {};

    for (let i = 0; i < elements.length; i++) {
        let values = elements[i].split("=");
        obj[values[0]] = values[1];
    }
    return obj;
}