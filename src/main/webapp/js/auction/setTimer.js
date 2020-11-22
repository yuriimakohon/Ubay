// document.addEventListener("DOMContentLoaded", function setTimer() {
//     // let countDownDate = new Date("Jan 5, 2021 15:37:25").getTime();
//     let countDownDate = document.getElementById("status_info-bottom").innerText;
//
//     let x = setInterval(function () {
//
//         let now = new Date().getTime();
//
//         let distance = countDownDate - now;
//
//         let days = Math.floor(distance / (1000 * 60 * 60 * 24));
//         let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
//         let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
//         let seconds = Math.floor((distance % (1000 * 60)) / 1000);
//
//         document.getElementById("status_info-bottom").innerHTML = days + "d " + hours + "h "
//             + minutes + "m " + seconds + "s ";
//
//         if (distance < 0) {
//             clearInterval(x);
//             document.getElementById("status_info-bottom").innerHTML = "EXPIRED";
//         }
//     }, 100);
// });

// let countDownDate = new Date("Jan 5, 2021 15:37:25").getTime();
let countDownDate = document.getElementById("status_info-bottom").getAttribute("data");

let x = setInterval(function () {

    let now = new Date().getTime();

    let distance = countDownDate - now;

    if (distance < 0) {
        clearInterval(x);
        location.reload();
    } else {
        let days = Math.floor(distance / (1000 * 60 * 60 * 24));
        let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("status_info-bottom").innerHTML = days + "d " + hours + "h "
            + minutes + "m " + seconds + "s ";
    }
}, 100);