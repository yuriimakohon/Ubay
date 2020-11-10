function addDays(days) {
    let date = new Date();
    date.setDate(date.getDate() + days);
    return date;
}

function dateToStringTime(date) {
    let string = date.getFullYear() + '-';

    if ((date.getMonth() + 1) <= 9)
        string += '0';
    string += (date.getMonth() + 1) + '-';

    if (date.getDate() <= 9)
        string += '0';
    string += date.getDate() + 'T';

    if (date.getHours() <= 9)
        string += '0';
    string += date.getUTCHours() + ':';

    if (date.getMinutes() <= 9)
        string += '0';
    string += date.getUTCMinutes() + ':';

    if (date.getSeconds() <= 9)
        string += '0';
    string += date.getUTCSeconds();

    return string;
}

let maxStartTime = dateToStringTime(addDays(7));

$('#input-start_time').attr('max', maxStartTime);

let rangeDuration = $('#range-duration');
let pDuration = $('#p-duration');

function onRangeDuration() {
    pDuration.text(rangeDuration.val());
}

onRangeDuration();