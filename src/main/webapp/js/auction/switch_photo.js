let currentPhoto = $('.current-photo-item');

function onPhoto(id) {
    let chosen = $('#photo-' + id);
    $('.photo-item-active').removeClass('photo-item-active');
    chosen.addClass('photo-item-active');

    currentPhoto.attr('src', chosen.attr('src'));
}

onPhoto(0);