console.log("Hello World!");

document.getElementById("sub").addEventListener('click', function () {
    console.log('tl: ' + document.getElementById('title_auction').value);
    console.log('dc: ' + document.getElementById('description').value);
    console.log('sp: ' + document.getElementById('start_price').value);
    console.log('mp: ' + document.getElementById('max_price').value);
    console.log('st: ' + document.getElementById('start_time').value);
    console.log('dr: ' + document.getElementById('duration').value);
    for (let i = 0; document.getElementById('forImage').files[i] != null; i++ ) {
        console.log('im: ' + document.getElementById('forImage').files[i].name);
    }
})