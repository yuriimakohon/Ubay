document.addEventListener("DOMContentLoaded", async function () {
    let lot = await get_auction();
    if (lot != null) {
        let rd = $('#range-duration');
        $('#input-title').val(lot.title);
        $('#textarea-description').val(lot.description);
        // $('#input-photos').val(lot.photo);
        $('#input-start_price').val(lot.price);
        $('#input-max_price').val(lot.maxPrice);
        // $('#input-start_time').val(lot.startTime);
        rd.val((+lot.duration - +lot.startTime) / 24 / 3600 / 1000);
        $('#p-duration').text(rd.val());

        let collectibles = $('#checkbox-collectibles');
        let technics = $('#checkbox-technics');
        let furniture = $('#checkbox-furniture');
        let clothing = $('#checkbox-clothing');
        let household = $('#checkbox-household');
        let jewellery = $('#checkbox-jewellery');

        if (lot.category.indexOf('collectibles') !== -1)
            collectibles.prop('checked', true);
        if (lot.category.indexOf('technics') !== -1)
            technics.prop('checked', true);
        if (lot.category.indexOf('furniture') !== -1)
            furniture.prop('checked', true);
        if (lot.category.indexOf('clothing') !== -1)
            clothing.prop('checked', true);
        if (lot.category.indexOf('household') !== -1)
            household.prop('checked', true);
        if (lot.category.indexOf('jewellery') !== -1)
            jewellery.prop('checked', true);

    }
})


