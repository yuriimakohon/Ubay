let infoText = $('#info_text');

function hideInfoText() {
    infoText.addClass('hidden');
}

function showInfoText() {
    infoText.removeClass('hidden');
}

function setErrorText(error) {
    infoText.text(error);
    infoText.addClass('error_text');
    infoText.removeClass('success_text');
    setTimeout(hideInfoText, 5000);

}

function setSuccessText(msg) {
    infoText.text(msg);
    infoText.addClass('success_text');
    infoText.removeClass('error_text');
    setTimeout(hideInfoText, 3000);
}

//=============| Error Messages |================//
function errMsg(msg) {
    setErrorText(msg);
    showInfoText();
}

function errLowBid() {
    errMsg('Your bid can\'t be lower than current highest bid');
}

//=============| Success Messages |================//
function successMsg(msg) {
    setSuccessText(msg);
    showInfoText();
}

function successBid() {
    successMsg('Your bid ' + inputBid.val() + '$ was successfully placed!');
}

function successWon() {
    successMsg('You have placed the maximum bid and won');
}