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
function errEmptyFields() {
    setErrorText('Fill all fields');
    showInfoText();
}

function errMaxPrice() {
    setErrorText('Max price can\'t be lower or equal to Start price');
    showInfoText();
}

//=============| Success Messages |================//
