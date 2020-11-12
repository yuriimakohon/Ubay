function onStar(evaluation) {
  $('#user-evaluation').text(evaluation);

  for (let i = 1; i <= evaluation; i++) {
    $('#star-' + i).addClass('evaluation_start-active');
  }
  evaluation++;
  for (; evaluation < 6; evaluation++) {
    $('#star-' + evaluation).removeClass('evaluation_start-active');
  }
}