let tabSignup = $('#tab-signup');
let tabSignin = $('#tab-signin');

let signupContent = $('#auth_content-signup');
let signinContent = $('#auth_content-signin');

function onTabSigin() {
    tabSignup.removeClass('active');
    tabSignin.addClass('active');

    signupContent.addClass("hidden");
    signinContent.removeClass("hidden");
    hideErrorText();
}
function onTabSigup() {
    tabSignin.removeClass('active');
    tabSignup.addClass('active');

    signupContent.removeClass("hidden");
    signinContent.addClass("hidden");
    hideErrorText();
}
