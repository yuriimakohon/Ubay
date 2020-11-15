function getBase64(file) {
    const reader = new FileReader()
    return new Promise(resolve => {
        reader.onload = ev => {
            resolve(ev.target.result)
        }
        reader.readAsDataURL(file)
    })
}

async function loadFiles(element) {
    let arr = [];

    for (let i = 0; i < element.files.length; i++ ) {
        arr.push(getBase64(element.files[i]))
    }
    return await Promise.all(arr)
}