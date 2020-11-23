function onStatus(num) {
    let allBox = $('.auction-item');
    let checkBoxAll = $('#checkbox-all');
    let check_not_active = $('#checkbox-notActive');
    let check_active = $('#checkbox-active');
    let check_finished = $('#checkbox-finished');

    allBox.each(function () {
        $(this).removeClass('hidden');
        $(this).addClass('hidden');
    });

    switch (num) {
        case 0:
            onAuctionAll(checkBoxAll, check_not_active, check_active, check_finished, allBox);
            break;
        case 2:
            onActive(checkBoxAll, check_not_active, check_active, check_finished);
            break;
        case 1:
            onNotActive(checkBoxAll, check_not_active, check_active, check_finished);
            break;
        case 3:
            onFinished(checkBoxAll, check_not_active, check_active, check_finished);
            break;
    }
}

function onAuctionAll(checkBoxAll, check_not_active, check_active, check_finished, allBox) {
    checkBoxAll.prop('checked', true);
    check_not_active.prop('checked', false);
    check_active.prop('checked', false);
    check_finished.prop('checked', false);
    allBox.each(function () {
        $(this).removeClass('hidden');
    });
}

function onActive(checkBoxAll, check_not_active, check_active, check_finished) {
    if (check_active.is(':checked')) {
        checkBoxAll.prop('checked', false);
        check_not_active.prop('checked', false);
        check_finished.prop('checked', false);
        $('.2').each(function () {
            $(this).removeClass('hidden');
        });
    } else {
        onStatus(0);
    }
}

function onNotActive(checkBoxAll, check_not_active, check_active, check_finished) {
    if (check_not_active.is(':checked')) {
        checkBoxAll.prop('checked', false);
        check_active.prop('checked', false);
        check_finished.prop('checked', false);
        $('.1').each(function () {
            $(this).removeClass('hidden');
        });
    } else {
        onStatus(0);
    }
}

function onFinished(checkBoxAll, check_not_active, check_active, check_finished) {
    if (check_finished.is(':checked')) {
        checkBoxAll.prop('checked', false);
        check_active.prop('checked', false);
        check_not_active.prop('checked', false);
        $('.3').each(function () {
            $(this).removeClass('hidden');
        });
    } else {
        onStatus(0);
    }
}

