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

function errPhotoCount() {
    setErrorText('Too much photos uploaded: 6 is maximum');
    showInfoText();
}

function errStartTime() {
    setErrorText('Invalid Start time: minimum is 1 hour after current moment');
    showInfoText();
}

function errTitleFormat() {
    setErrorText('Title can contain only alpha-numeric symbols, \',\' and \'!\'');
    showInfoText();
}

function errDescriptionFormat() {
    setErrorText('Title can contain only alpha-numeric symbols, \',\' and \'!\'');
    showInfoText();
}

//=============| Success Messages |================//

