function addDays(date, days) {
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

let maxStartTime = dateToStringTime(addDays(new Date(), 7));

$('#input-start_time').attr('max', maxStartTime);

let rangeDuration = $('#range-duration');
let pDuration = $('#p-duration');

function onRangeDuration() {
    pDuration.text(rangeDuration.val());
}

onRangeDuration();


function datetimeToData(datetime) {
    let st = new Date();

    st.setFullYear(parseInt(datetime.split("T")[0].split("-")[0]))
    st.setMonth(parseInt(datetime.split("T")[0].split("-")[1]) - 1);
    st.setDate(parseInt(datetime.split("T")[0].split("-")[2]));
    st.setHours(parseInt(datetime.split("T")[1].split(":")[0]));
    st.setMinutes(parseInt(datetime.split("T")[1].split(":")[1]));

    return st;
}

function checkMinTime(startTime) {
    let st = datetimeToData(startTime);

    let minTime = new Date();
    minTime.setMinutes(st.getMinutes() + 5);

    return st.getTime() >= minTime.getTime();
}