let currentPhoto = $('.current-photo-item');

function onPhoto(id) {
    let chosen = $('#photo-' + id);
    let src = chosen.attr('src');

    $('.photo-item-active').removeClass('photo-item-active');
    chosen.addClass('photo-item-active');
    currentPhoto.attr('src', src);
    $('.photos-view a').attr('href', src);
}

onPhoto(0);