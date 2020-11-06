let errorText = $('#error_text');

function hideErrorText() {
    errorText.addClass("hidden");
}

function showErrorText() {
    errorText.removeClass("hidden");
}

function errMissingFields() {
    errorText.text("Fill all fields");
    showErrorText();
}

function errLoginFormat() {
    errorText.text("Login may have 3-21 characters. Contains only numbers and latin letters");
    showErrorText();
}

function errPassConfirm() {
    errorText.text("Passwords must match");
    showErrorText();
}

function errPassFormat() {
    errorText.text("Password may have at least 3 characters. Contains only numbers and latin letters");
    showErrorText();
}

function errUserExist() {
    errorText.text("User with the same login is already exist");
}