let tabSignup = document.getElementById("tab-signup");
let tabSignin = document.getElementById("tab-signin");

function onTabSigin() {
    tabSignup.classList.remove("active");
    tabSignin.classList.add("active");
}
function onTabSigup() {
    tabSignin.classList.remove("active");
    tabSignup.classList.add("active");
}