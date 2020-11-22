function feedbackHtmlGen(login, text, mark, userId, photo) {
  let html = ['            <div class="feedback card">\n' +
  '                <div class="feedback-info-container">\n' +
  '                    <div class="feedback_author" onclick="onUser(' + userId + ')">\n' +
  '                        <img src="' + photo + '">\n' +
  '                        <span>' + login + '</span>\n' +
  '                    </div>\n' +
  '                    <div class="feedback-evaluation-container">\n'];

  for (let i = 0; i < mark; i++) {
    html += '                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">\n';
  }
  for (; mark < 5; mark++) {
    html += '                        <img class="evaluation_start" src="/resources/start.svg" alt="star">\n';
  }

  html += ['                    </div>\n' +
  '                </div>\n' +
  '                <p class="feedback-text">' + text + '<p>\n' +
  '            </div>'];
  return html;
}


async function onFeedbacks(id) {
  $('.feedbacks').removeClass('hidden');
  $('#btn-feedbacks-container').addClass('hidden');
  let container = $('#feedbacks-container');

  let response = await fetch('http://localhost:8080/api/feedback/' + id, {
    method: 'GET',
    credentials: "same-origin"
  });

  if (response.ok) {
    let json = await response.json();

    json.forEach(el => {
      let comment = el['comment'];
      let mark = el['mark'];
      let userId = el['userId'];
      let login = el['login'];
      let avatar = el['avatar'];

      let feedback = feedbackHtmlGen(login, comment, mark, userId, avatar);

      container.prepend(feedback);
    });
  } else {
    console.log("BAD FEEDBACK-REQ RESPONSE");
  }
}













// <div class="feedback card">
//   <div class="feedback-info-container">
//     <div class="feedback_author">
//       <img src="/resources/test2.jpeg">
//         <span>user_login</span>
//     </div>
//     <div class="feedback-evaluation-container">
//       <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//         <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//           <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//             <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//               <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
//     </div>
//   </div>
//   <p class="feedback-text">
//     Lorem ipsum dolor sit amet, consectetur adipiscing elit,
//     sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
//     Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
//     nisi ut aliquip ex ea commodo consequat.
//   </p>
// </div>