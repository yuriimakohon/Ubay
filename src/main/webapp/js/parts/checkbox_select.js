function onAuctionAll() {
    let allBox = $('.auction-item');
    let checkBoxAll = $('#checkbox-all');

    if (checkBoxAll.is(':checked')) {
        $('#checkbox-notActive').prop('checked', false);
        $('#checkbox-active').prop('checked', false);
        $('#checkbox-finished').prop('checked', false);
        allBox.each(function () {
            $(this).removeClass('hidden');
        });
    }
}

function onStatus() {
    let allBox = $('.auction-item');
    let checkBoxAll = $('#checkbox-all');
    checkBoxAll.prop('checked', false)


    allBox.each(function () {
        $(this).removeClass('hidden');
        $(this).addClass('hidden');
    });

    if ($('#checkbox-notActive').is(':checked')) {
        $('.1').each(function () {
            $(this).removeClass('hidden');
        });
    }
    if ($('#checkbox-active').is(':checked')) {
        $('.2').each(function () {
            $(this).removeClass('hidden');
        });
    }
    if ($('#checkbox-finished').is(':checked')) {
        $('.3').each(function () {
            $(this).removeClass('hidden');
        });
    }
}
