function lotsGen(json) {
    json.lots.forEach(el => {
        let parsed = JSON.parse(el);
        console.log(parsed['title'] + "\n" + parsed['description']);
    });
    console.log(json.lots[0]);
}