let pRate = $('#p-rate');
let rangeRate = $('#range-rate');

function onRangeRate() {
    pRate.text(rangeRate.val());
}

onRangeRate();